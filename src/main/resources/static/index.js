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
			username : $("#username").val(),
			'flexAttrs[name]': $("#name").val(),
			'flexAttrs[age]': $("#age").val()
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
        contentType : "application/json;charset=utf-8",
		data : JSON.stringify({
			username : $("#edit-username").val(),
			flexAttrs:{
                name: $("#edit-name").val(),
                age: $("#edit-age").val()
			}
		}),
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
	});
	return false;
}).on('click', '.batch-delete-btn', function () {
    var data = [];
    $('[name=userId]:checked').each(function (i) {
		data.push(this.value);
    });
    $.ajax({
        url : "/users",
		data: JSON.stringify({ids: data}),
        method : "delete",
		contentType: 'application/json;charset=UTF-8',
        success : function(resp) {
            console.info(resp)
            loadUsers();
        }
    });
    return false;
});