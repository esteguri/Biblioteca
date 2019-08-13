function listarSelect() {
    $.get("http://localhost:8080/Biblioteca/categoria",{}, function(response){
        for (var i = 0; i<response.length; i++){
            $("#categorias").append("<option value='"+response[i].id+"'>"+response[i].categoria+"</option>")
        }
    })
}

function consultarCategoria(name){
    $.get("http://localhost:8080/Biblioteca/categoria", {'search':'name', 'name':name}, function(response){
        if (response.error){
            $("#consultarCategoria span").removeClass("d-none")
        }else{
            $("#libros").css("display","none");
            $("#consultarCategoria span").addClass("d-none")
            $("#categoria").css("display","block");
            $("#categoria tbody tr").html("<td>"+response.id+"</td><td>"+response.categoria.toUpperCase()+"</td>")
        }
    })
}

function consultarLibrosxCategoria(categoria){
    $.get("http://localhost:8080/Biblioteca/libro", {'search':'categoria', 'categoria':categoria}, function(response){
        if (response.length==0 || response.error){
            $("#consultarLibroXcategoria span").removeClass("d-none")
            $("#libros tbody").html("");
        }else{
            $("#libros").css("display","block");
            $("#consultarLibroXcategoria span").addClass("d-none")
            $("#categoria").css("display","none");
            $("#libros tbody").html("");
            for (var i = 0; i<response.length; i++){

                $("#libros tbody").append('<tr>\n' +
                    '                        <td><strong>'+response[i].id+'</strong></td>\n' +
                    '                        <td>'+response[i].nombre+'</td>\n' +
                    '                        <td><span class="badge badge-primary">'+response[i].autor+'</span></td>\n' +
                    '                        <td>'+response[i].idCategoria+'</td>\n' +
                    '                        <td><span class="badge badge-warning">'+response[i].estante+'</span></td>\n' +
                    '                    </tr>')
            }

        }
    })
}

function consultarLibro(name) {
    $.get("http://localhost:8080/Biblioteca/libro",{'search':'name','name':name}, function (response) {
        console.log(response);
        if (response.error){
            $("#consultarLibro span").removeClass("d-none")
        }else{
            $.get("http://localhost:8080/Biblioteca/categoria", {'search':'id', 'id':response.idCategoria},function (resp) {
                $("#libros").css("display","block");
                $("#consultarLibro span").addClass("d-none")
                $("#categoria").css("display","none");
                $("#libros tbody").html("");
                $("#libros tbody").append('<tr>\n' +
                    '                        <td><strong>'+response.id+'</strong></td>\n' +
                    '                        <td>'+response.nombre+'</td>\n' +
                    '                        <td><span class="badge badge-primary">'+response.autor+'</span></td>\n' +
                    '                        <td>'+resp.categoria+'</td>\n' +
                    '                        <td><span class="badge badge-warning">'+response.estante+'</span></td>\n' +
                    '                    </tr>')
            })

        }
    })
}

function consultarCategoria(id) {
    let categoria = null;
    $.get("http://localhost:8080/Biblioteca/categoria", {'search':'id', 'id':id},function (response) {
        console.log(response)
        console.log(response.categoria)
        categoria = response.categoria;
    })

    console.log(categoria)

    return "" + categoria
}

function limpiarSpan() {
    $("#consultarCategoria span").addClass("d-none")
    $("#consultarLibroXcategoria span").addClass("d-none")
    $("#consultarLibro span").addClass("d-none")
}

function agregarLibro(nombre, autor, categoria, estante) {
    $.post("http://localhost:8080/Biblioteca/libro",{'libro':'{"nombre":"'+nombre+'","autor":"'+autor+'","idCategoria":'+categoria+',"estante":"'+estante+'"}'}, function (response) {
        if (response.error == "200"){
            mostrarAlerta(true, "Se inserto correctamente")
        }else{
            mostrarAlerta(false, "Problemas en la insercion")
        }
    })
}

function crearCategoria(categoria){
    $.post("http://localhost:8080/Biblioteca/categoria",{'categoria':"{'categoria':'"+categoria+"'}"}, function(response) {
        if (response.error == "200"){
            mostrarAlerta(true, "Se creo correctamente")
        }else{
            mostrarAlerta(false, "Problemas en la creacion")
        }
    })
}

function  mostrarAlerta(boolean, mensaje) {

    if (boolean){
        $("#ok div").html(mensaje)
        $("#ok").addClass("d-block");
        setTimeout(function () {
            $("#ok").removeClass("d-block");
        },2000)
    } else{
        $("#error div").html(mensaje)
        $("#error").addClass("d-block");
        setTimeout(function () {
            $("#error").removeClass("d-block");
        },2000)
    }


}