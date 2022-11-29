var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });
        $('#btn-update').on('click', function (){
            _this.update();
        });
        $('#btn-delete').on('click', function (){
            _this.delete();
        });
    },
    save : function () {
        var data = {
            p_name: $('#p_name').val(),
            p_price: $('#p_price').val(),
            p_desc: $('#p_desc').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/products',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            p_name: $('#p_name').val(),
            p_price: $('#p_price').val(),
            p_desc: $('#p_desc').val()
        };

        var p_id = $('#p_id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/products/'+p_id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    delete : function () {

        var p_id = $('#p_id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/products/'+p_id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();