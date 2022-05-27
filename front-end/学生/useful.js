// 获取JSON对象的长度
function getJsonLength(json){
					var length = 0;
					for (var ever in json) {
						length++;
					}
					return length;
				}