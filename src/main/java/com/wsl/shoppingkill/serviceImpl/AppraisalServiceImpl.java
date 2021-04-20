package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;

import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.mapper.GoodsMapper;
import com.wsl.shoppingkill.mapper.OrderMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.domain.Appraisal;
import com.wsl.shoppingkill.mapper.AppraisalMapper;
import com.wsl.shoppingkill.obj.vo.AppraisalUserVO;
import com.wsl.shoppingkill.obj.vo.AppraisalVO;
import com.wsl.shoppingkill.service.AppraisalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 */
@Service
public class AppraisalServiceImpl extends ServiceImpl<AppraisalMapper, Appraisal> implements AppraisalService {

    @Resource
    private AppraisalMapper appraisalMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;


    @Override
    public IPage<AppraisalVO> getAppraisalAll(Long current, Long size, AppraisalVO appraisalVO) {
        final IPage<AppraisalVO> appraisalAll = appraisalMapper.getAppraisalAll(new Page<>(current, size), appraisalVO);
        if (CollectionUtils.isNotEmpty(appraisalAll.getRecords())) {
            appraisalAll.getRecords().forEach(li -> li.setUserName(li.getUserName()));
        }
        return appraisalAll;
    }

    @Override
    public List<AppraisalUserVO> getAppraisalAll(boolean flag) {
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        int status = 0;

        if (flag) {
            status = BaseEnum.ORDER_TYPE_GET;
        } else {
            status = BaseEnum.ORDER_TYPE_TELL;
        }
        List<AppraisalUserVO> appraisal = orderMapper.selectGoodsInfo(id, status);
        if (flag) {
            return appraisal;
        }
        Map<String, Appraisal> collect = appraisalMapper.selectList(new QueryWrapper<Appraisal>().in(Appraisal.GOODS_ID,
                appraisal.stream()
                        .map(AppraisalUserVO::getOrderId)
                        .collect(Collectors.toList()))
        ).stream().collect(Collectors.toMap(Appraisal::getOrderId, Function.identity()));
        appraisal.forEach(li -> {
            String orderId = li.getOrderId();
            li.setImgUrl(li.getImgUrl() + "?x-oss-process=image/resize,m_fill,h_150,w_150");
            Appraisal appraisal1 = collect.get(orderId);
            if (Objects.nonNull(appraisal1)) {
                li.setCreatTime(appraisal1.getCreatTime())
                        .setDetail(appraisal1.getDetail())
                        .setGrade(appraisal1.getGrade())
                        .setId(appraisal1.getId());
            }
        });
        return appraisal.stream().filter(li -> li.getId() != null).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addAppraisal(Appraisal appraisal) {
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        try {
            boolean flag = appraisal.setUserId(id).insert();
            Order order = orderMapper.selectById(appraisal.getOrderId());
            if (BaseEnum.ORDER_TYPE_GET.equals(order.getStatus())) {
                boolean flag2 = orderMapper.updateById(order.setStatus(BaseEnum.ORDER_TYPE_END)) > 0;
                if (flag && flag2) {
                    return true;
                }
            }
            throw new Exception();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }


    @Override
    @MyLog(detail = "删除评价信息", grade = LoggerEnum.SERIOUS, value = "#id")
    public boolean delAppraisalById(Long id) {
        return appraisalMapper.deleteById(id) > 0;
    }

}
