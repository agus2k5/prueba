<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base_url" value="${pageContext.request.contextPath}"/>
<h5>Crear Autor</h5>
<form class="col s12" id="formautor">
    <div class="row">
        <div class="input-field col s6">
            <i class="material-icons prefix">account_circle</i>
            <input id="nombre_input" type="text" class="validate">
            <label for="icon_prefix">Nombre</label>
        </div>
    </div>
    <button class="btn waves-effect waves-light indigo"  type="" name="">Submit
        <i class="material-icons right" >send</i>
    </button>
</form>
<script>
    $(function () {
        $("#btn_insert").click(function (){insertAuthor();});
        $("#btn_list").click(function (){listAuthor();});
        //evento submit
        $("#formautor").submit(function (e) {
            //evitar la regarga de la pag al hacer submut
            e.preventDefault();
            //armar json para enviar, $("#") = buscar en DOM por ID,$(.)=buscar en DOM por CLASS, .val() = recuperar valor
            //var data_json = {nombre: $("#nombre_input").val()};
            //post contra autores/insert, va a devolver un json el server, por lo tanto es necesaria la cabecera accept json
            $.ajax({
                type: "POST",
                url: "${base_url}/autores/insert",
                headers: {
                    Accept: "application/json"
                },
                data: {nombre:$("#nombre_input").val()},
                success: function (data) {
                    /*el json viene dentro de un string, entonces primero se lo castea a estructura json
                     * y luego a json object para poder acceder a los elementos (obj.attr)*/
                    var json = JSON.parse(JSON.stringify(data));
                    //.html()=pisar el html, .append()=seguir escribiendo 
                    $("#mensaje").html("<h5>" + json.titulo + "</h5><p>" + json.mensaje + "</p>");
                }
            });

        });

    });
    
</script>
