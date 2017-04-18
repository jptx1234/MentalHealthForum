
function loadSummernote(){
	$('.summernote').summernote({
		fontNames: ['微软雅黑', '黑体', 'Comic Sans MS', 'Courier New'],
		height: 200,
		lang: 'zh-CN'
	});
	console.log("summernote初始化");
	updateForbitSubmitMask();
}

function forbitSubmit(msg){
	var parentDiv = $("div:has(>.forbit-submit-mask)");
	var maskDiv = $(".forbit-submit-mask"); 
	maskDiv.width(parentDiv.width());
	maskDiv.height(parentDiv.height());
	maskDiv.find("p").html(msg);
	maskDiv.show();
}

function updateForbitSubmitMask(){
	if(!window.user.login){
		forbitSubmit("请登录后再发帖<br /><a onclick='showLogin()'>登录</a>&nbsp;&nbsp;<a onclick='showReg()'>注册</a>");
	}
}

$(function() {
	loadSummernote();
});