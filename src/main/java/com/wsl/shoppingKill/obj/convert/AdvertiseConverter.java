package com.wsl.shoppingKill.obj.convert;

import com.wsl.shoppingKill.domain.Advertise;
import com.wsl.shoppingKill.obj.vo.AdvertiseVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/11/9 10:15 下午
 **/

@Mapper
public interface AdvertiseConverter {
    AdvertiseConverter CONVERTER = Mappers.getMapper(AdvertiseConverter.class);

    /**
     * advertise转VO -->List
     * @author : WangShiLei
     * @date : 2020/11/9 10:17 下午
     * @param advertises:
     * @return List<AdvertiseVO>
     **/
    List<AdvertiseVO> advertise2VO(List<Advertise> advertises);
}
