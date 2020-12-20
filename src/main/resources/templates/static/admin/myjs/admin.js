$.ajaxSetup({
	headers: {
		"Authorization": $.cookie("token")
	},
	xhrFields: {
		withCredentials: false
	},
	crossDomain: true
});
$(function() {
	if ($.cookie("token") == null || $.cookie("token") == "") {
		window.location.href = "/admin/login.html"
	}
});
$(function() {
	$("#admin").bootstrapTable("destroy");
	$("#admin").bootstrapTable({
		method: "get",
		url: "https://localhost/admin/getAdminList/v1",
		dataType: "json",
		headers: {
			"Authorization": $.cookie("token")
		},
		ajaxOptions: {
			xhrFields: {
				withCredentials: false
			},
			crossDomain: true
		},
		striped: true,
		pageNumber: 1,
		pagination: true,
		sidePagination: "server",
		pageSize: 15,
		pageList: [5, 10, 15, 20, 30, 50],
		showRefresh: false,
		silent: true,
		cache: false,
		queryParams: null,
		clickToSelect: true,
		responseHandler: a,
		columns: [{
			title: "Id",
			field: "id",
			edit: true,
			sortable: true
		},
		{
			title: "账户",
			field: "name",
		},
		{
			title: "姓名",
			field: "nickName",
			editable: {
				type: "text",
				title: "姓名",
				validate: function(b) {
					if (!b) {
						return "用户名不能为空"
					}
				}
			}
		},
		{
			title: "密码",
			field: "password",
			editable: {
				type: "text",
				title: "姓名",
				validate: function(b) {
					if (!b) {
						return "密码不能为空"
					}
				}
			}
		},
		{
			title: "手机号",
			field: "phone",
			editable: {
				type: "text",
				title: "姓名",
				validate: function(b) {
					if (!b) {
						return "手机号不能为空"
					}
				}
			}
		},
		{
			title: "家庭住址",
			field: "address",
			editable: {
				type: "text",
				title: "姓名",
				validate: function(b) {
					if (!b) {
						return "家庭住址不能为空"
					}
				}
			}
		},
		{
			title: "邮箱",
			field: "mail",
			editable: {
				type: "text",
				title: "姓名",
				validate: function(b) {
					if (!b) {
						return "家庭住址不能为空"
					}
				}
			}
		},
		{
			title: "创建时间",
			field: "creatTime",
			sortable: true
		},
		{
			title: "更新时间",
			field: "updateTime",
			sortable: true
		},
		{
			title: "操作",
			field: "grade",
			formatter: function(c, d, b) {
				return '<button type="button" class="btn  btn-danger" style="margin:5px" onclick=" delActivity(' + d.id + ')">删除人员</button>'
			}
		}],
		onEditableSave: function(d, e, b, c) {
			if (e.sex == "男") {
				e.sex = "MAN"
			} else {
				e.sex = "WOMAX"
			}
			$.ajax({
				type: "put",
				url: "https://localhost/admin/updateAdmin/v1",
				data: e,
				dataType: "JSON",
				success: function(f) {
					if (f.code == 0 && f.data == true) {
						alert("修改成功")
					} else {
						alert("修改失败")
					}
				},
				error: function() {
					alert("编辑失败")
				},
				complete: function() {
					$("#admin").bootstrapTable("refresh")
				}
			})
		}
	});
	function a(b) {
		return {
			"rows": b.data
		}
	}
});
$("#subs").click(function() {
	$("#addAdmin").bootstrapValidator("validate");
	$("#addAdmin").data("bootstrapValidator").validate();
	if ($("#addAdmin").data("bootstrapValidator").isValid()) {
		$("#addAdmin").ajaxSubmit(function(a) {
			if (a.code == 0) {
				alert("编辑成功");
				$("#admin").bootstrapTable("refresh");
				window.location.reload()
			} else {
				alert(a.userMsg)
			}
		})
	}
});
function delActivity(b) {
	var a = confirm("确认删除吗？");
	if (a == true) {
		$.ajax({
			url: "https://localhost/admin/delAdmin/v1",
			data: {
				id: b
			},
			type: "delete",
			success: function(c) {
				if (c.code == 0) {
					alert("删除成功");
					$("#admin").bootstrapTable("refresh")
				} else {
					alert(c.userMsg)
				}
			},
			error: function(c) {
				alert(c.userMsg)
			}
		})
	}
}
$("#addAdmin").bootstrapValidator({
	message: "This value is not valid",
	feedbackIcons: {
		valid: "glyphicon glyphicon-ok",
		invalid: "glyphicon glyphicon-remove",
		validating: "glyphicon glyphicon-refresh"
	},
	fields: {
		name: {
			message: "账户无效",
			validators: {
				notEmpty: {
					message: "账户不能为空"
				}
			}
		},
		password: {
			message: "密码无效",
			validators: {
				notEmpty: {
					message: "密码不能为空"
				},
				stringLength: {
					min: 6,
					max: 30,
					message: "密码长度必须在6到30之间"
				}
			}
		},
		email: {
			validators: {
				notEmpty: {
					message: "邮箱不能空"
				},
				emailAddress: {
					message: "邮箱地址不正确"
				}
			}
		},
		sex: {
			validators: {
				notEmpty: {
					message: "性别不能空"
				}
			}
		},
		nickName: {
			validators: {
				notEmpty: {
					message: "姓名不能空"
				}
			}
		},
		idCard: {
			validators: {
				notEmpty: {
					message: "身份证号不能空"
				},
				regexp: {
					regexp: /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/,
					message: "身份证号码格式不正确，为15位和18位身份证号码！"
				}
			}
		},
		phone: {
			validators: {
				notEmpty: {
					message: "手机号不能空"
				},
				regexp: {
					regexp: /^1[34578]\d{9}$/,
					message: "请输入完整手机号码！"
				}
			}
		},
		address: {
			validators: {
				notEmpty: {
					message: "家庭住址不能空"
				}
			}
		}
	}
});