_.templateSettings = {
	evaluate : /{{([\s\S]+?)}}/g,
	interpolate : /{{=([\s\S]+?)}}/g,
	escape : /{{-([\s\S]+?)}}/g
};
function loadUsers() {
	$.ajax({
		url : "/users",
		method : 'get',
		success : function(resp) {
			console.debug(resp)
			var tmpl = _.template($("#users-tmpl").html());
			var html = tmpl({
				users : resp.res
			});
			console.debug(html)
			$("#all-users").html(html);
		}
	})
}
loadUsers();
$(".container").on('submit', '#add-form', function() {
	$.ajax({
		url : "/user",
		method : "post",
		data : {
			username : $("#username").val()
		},
		success : function(resp) {
			console.info(resp)
			loadUsers();
		}
	})
	return false;
}).on('click', '.edit-btn', function() {
	var id = $(this).data('id');
	var name = $(this).data('name');
	var input = $("#edit-username").val(name).data('id', id);
}).on('submit', '#edit-form', function() {
	var id = $("#edit-username").data('id');
	$.ajax({
		url : "/user/" + id,
		method : "put",
		data : {
			username : $("#edit-username").val()
		},
		success : function(resp) {
			console.info(resp)
			loadUsers();
		}
	})
	return false;
}).on('click', '.delete-btn', function() {
	var id = $(this).data('id');
	$.ajax({
		url : "/user/" + id,
		method : "delete",
		success : function(resp) {
			console.info(resp)
			loadUsers();
		}
	})
	return false;
});