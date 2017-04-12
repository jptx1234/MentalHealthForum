$(function() {
				$.get("navbar.html", {}, function(data) {
					$("body").prepend(data);
				});
				$.get("footer.html", {}, function(data) {
					$("body").append(data);
				});
			}
);