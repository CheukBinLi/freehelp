var ajax = {
	base : function(URL, DATA, TYPE, DATA_TYPE, SUCCESS_FUN, ERROR_FUN) {
		$.ajax({
			type : TYPE,
			url : URL,
			dataType : DATA_TYPE,
			data : DATA,
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus, jqXHR) {
				if (undefined != SUCCESS_FUN) {
					SUCCESS_FUN(data, textStatus, jqXHR);
				}
				// alert(data);
				// location.href = "/spring_crud_restful/emp/listEmp/1";
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				// 通常 textStatus 和 errorThrown 之中
				// 只有一个会包含信息
				// this; // 调用本次AJAX请求时传递的options参数
				if (undefined != ERROR_FUN) {
					ERROR_FUN(XMLHttpRequest, textStatus, errorThrown);
				}
			}
		});
	},
	POST : function(URL, DATA, SUCCESS_FUN, ERROR_FUN) {
		this.base(URL, DATA, "POST", "json", SUCCESS_FUN, ERROR_FUN);
	},
	GET : function(URL, DATA, SUCCESS_FUN, ERROR_FUN) {
		this.base(URL, DATA, "GET", "json", SUCCESS_FUN, ERROR_FUN);
	},
	PUT : function(URL, DATA, SUCCESS_FUN, ERROR_FUN) {
		this.base(URL, DATA, "PUT", "json", SUCCESS_FUN, ERROR_FUN);
	},
	DELETE : function(URL, DATA, SUCCESS_FUN, ERROR_FUN) {
		this.base(URL, DATA, "DELETE", "json", SUCCESS_FUN, ERROR_FUN);
	}
}
var msgModel = {
	getCode : function(data) {
		return data.code
	},
	getMsg : function(data) {
		if (undefined != data.msg)
			return data.msg;
		return '';
	},
	getData : function(data) {
		if (undefined != data.data)
			return data.data;
		return '';
	},
	getAttachment : function(data) {
		if (undefined != data.attachment)
			return data.attachment;
		return '';
	}
}

var messageModel = function(data) {
	// this.code = data.code;
	// this.msg = data.msg;
	// this.data = data.data;
	// this.attachment = data.attachment;

	var obj = new Object();
	obj.data = data
	obj.getCode = function() {
		return this.data.code;
	}
	obj.getmsg = function() {
		if (undefined != this.data.msg)
			return this.data.msg;
		return null;
	}
	obj.getData = function() {
		if (undefined != this.data.data)
			return this.data.data;
		return null;
	}
	obj.getAttachment = function() {
		if (undefined != this.data.attachment)
			return this.data.attachment;
		return null;
	}
	return obj;
}
