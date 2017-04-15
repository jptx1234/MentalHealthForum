function getParameterByName(name, url) {
    if (!url) {
      url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

window.user = {"login":false};

$.ajax({
	type:"get",
	url:"user_getUserInfo",
	async:false,
	data:{},
	dataType:"json",
	success:function(data){
		if(data.status == 1){
			window.user = data;
			window.user["login"]=true;
		}
	}
});

