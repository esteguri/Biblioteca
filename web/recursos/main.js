$(document).ready(function(){
    listarSelect()
})

function listarSelect(){
    $.get("http://localhost:8080/Biblioteca/categoria",{}, function(response){
        console.log(response)
    })
}