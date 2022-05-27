window.onload = function() {
	username = ReadCookie("username");
	// 发送一个username，返回包含该用户所有简历名的json，
	// 处理后显示所有该用户简历的链接
	$.ajax({
		type: "POST",
		url: "http://127.0.0.1:8080/showResumes",
		data: {
			username: username
		},
		success: function(response) {
			// 收到后自动生成简历选项
			var list = response.split("$")
			for (var i = 1; i < list.length; i++) {
				var a = list[i].indexOf(":");
				var order = list[i].substring(0, a);
				var resumeName = list[i].substring(a + 1);
				
				document.getElementById('resumes').innerHTML += "<input type='button' id='" +
					resumeName + "' value='" + resumeName + "'class = 'resume'"
					+ "onclick=\" setCookie('resumeOrder'," + order +
					" ); window.location = '简历详情.html';\"" + "'/><br><br>"
			}
		}
	})

	// 给新建简历按钮绑定单击事件
	document.getElementById("create").onclick = function() {
		setCookie("resumeOrder", '');
		window.location = '简历详情.html';
	}
}
