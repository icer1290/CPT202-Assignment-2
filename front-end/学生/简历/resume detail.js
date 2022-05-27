window.onload = function() {
	var username = ReadCookie("username");
	var order = ReadCookie("resumeOrder");


	// 验证简历名格式
	var nameErrorSpan = document.getElementById("nameError");
	// 给id="resumeName"的节点绑定blur事件
	var resumeNameElt = document.getElementById("resumeName");
	resumeNameElt.onblur = function() {
		var resumeName = resumeNameElt.value;
		resumeName = resumeName.trim();
		if (resumeName == "") {
			nameErrorSpan.innerHTML = "简历名不能为空";
		} else {
			if (resumeName.length < 1 || resumeName.length > 20) {
				nameErrorSpan.innerHTML = "简历名长度必须在[1-20]之间";
			} else {
				var regExp = /^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/
				var ok = regExp.test(resumeName)
				if (ok) {
					nameErrorSpan.innerHTML = "";
				} else {
					nameErrorSpan.innerHTML = "请输入正确格式！";
				}
			}
		}
	}
	resumeNameElt.onfocus = function() {
		nameErrorSpan.innerHTML = "";
	}

	// 预先填充已知信息
	if (order != "") {
		$.ajax({
			type: "POST",
			url: "http://127.0.0.1:8080/resumeShowing",

			data: { //JSON对象
				username: username,
				r_order: order
			},
			//请求成功则调用函数
			success: function(response) {
				document.getElementById('createTime').innerHTML = response["updateTime"];
				document.getElementById('resumeName').value = response["r_name"];
				document.getElementById('stuName').value = response["stu_name"];
				document.getElementById('projectExp').value = response["r_project"];
				document.getElementById('learnCourse').value = response["r_course"];
				document.getElementById('hobby').value = response["r_hobby"];
				document.getElementById('specialty').value = response["r_skill"];
				document.getElementById('awards').value = response["r_award"];
				document.getElementById('workExp').value = response["r_job"];
				document.getElementById('comment').value = response["r_evaluate"];
			}
		})
	}


	document.getElementById("Edit").onclick = function() {
		document.getElementById("resumeName").focus();
		document.getElementById("resumeName").blur();
		//点击编辑按钮获取当前日期
		var nowTime = document.getElementById('createTime')
		//若是新建简历，则获取本地日期
		if (document.getElementById('createTime').innerHTML == '' && nameErrorSpan.innerHTML == '') {
			var nowTime = new Date();
			nowTime = nowTime.toLocaleDateString();
			document.getElementById('createTime').innerHTML = nowTime;
			$.ajax({
				type: "POST",
				url: "http://127.0.0.1:8080/createResume",

				data: { //JSON对象
					username: username,
					updateTime: nowTime,
					r_name: document.getElementById("resumeName").value,
					stu_name: document.getElementById("stuName").value,
					r_project: document.getElementById("projectExp").value,
					r_course: document.getElementById("learnCourse").value,
					r_hobby: document.getElementById("hobby").value,
					r_skill: document.getElementById("specialty").value,
					r_award: document.getElementById("awards").value,
					r_job: document.getElementById("workExp").value,
					r_evaluate: document.getElementById("comment").value
				},
				//请求成功则调用函数
				success: function(data) {
					// alert("1")
					if (data == 1) {
						alert("创建成功")
					} else {
						alert("创建失败")
					}
				}
			})
		} else if (nameErrorSpan.innerHTML == "") {
			//简历已经存在，则改变为当前日期
			var nowTime = new Date();
			nowTime = nowTime.toLocaleDateString();
			document.getElementById('createTime').innerHTML = nowTime;
			$.ajax({
				type: "POST",
				url: "http://127.0.0.1:8080/setResume",

				data: {
					order: order,
					username: username,
					updateTime: nowTime,
					r_name: document.getElementById("resumeName").value,
					stu_name: document.getElementById("stuName").value,
					r_project: document.getElementById("projectExp").value,
					r_course: document.getElementById("learnCourse").value,
					r_hobby: document.getElementById("hobby").value,
					r_skill: document.getElementById("specialty").value,
					r_award: document.getElementById("awards").value,
					r_job: document.getElementById("workExp").value,
					r_evaluate: document.getElementById("comment").value
				},

				success: function(data) {
					if (data == 11) {
						alert("保存成功")
					} else {
						alert("保存失败")
					}
				}
			})
		}
	}
	//删除按钮
	document.getElementById("Delete").onclick = function() {
		$.ajax({
			type: "POST",
			url: "http://127.0.0.1:8080/deleteResume",

			data: {
				username: username,
				r_order: order
			},

			success: function(data) {
				if (data == 1) {
					alert("删除成功")
					window.location = '简历选择.html'
				} else {
					alert("还没创建该简历！")
				}
			}
		})
	}
}
