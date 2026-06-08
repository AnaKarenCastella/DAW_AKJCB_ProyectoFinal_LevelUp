<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.app.model.Usuario" %>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfil");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Perfil - LevelUp</title>

    <style>

        body{
            margin:0;
            font-family:Arial, Helvetica, sans-serif;
            background:#0b0f14;
            color:white;
        }

        .container{
            max-width:700px;
            margin:50px auto;
        }

        .card{
            background:#121821;
            border:1px solid rgba(0,191,255,.18);
            border-radius:14px;
            padding:30px;
        }

        h1{
            margin-bottom:25px;
        }

        .form-group{
            margin-bottom:18px;
        }

        label{
            display:block;
            margin-bottom:6px;
            color:#8fa1b3;
            text-transform:uppercase;
            font-size:12px;
            font-weight:bold;
        }

        input{
            width:100%;
            padding:12px;
            border-radius:8px;
            border:1px solid rgba(0,191,255,.20);
            background:#0b0f14;
            color:white;
            box-sizing:border-box;
        }

        .btn{
            padding:12px 18px;
            border:none;
            border-radius:8px;
            font-weight:bold;
            cursor:pointer;
        }

        .btn-save{
            background:#00bfff;
            color:#061018;
        }

        .btn-cancel{
            background:#06283a;
            color:#00bfff;
            text-decoration:none;
            margin-left:10px;
        }

        .actions{
            margin-top:20px;
        }

    </style>
</head>

<body>

<div class="container">

    <div class="card">

        <h1>Editar Perfil</h1>

        <form action="<%= request.getContextPath() %>/editarPerfil"
              method="post">

            <div class="form-group">
                <label>Nombre</label>

                <input type="text"
                       name="nombre"
                       value="<%= usuario.getNombre() %>"
                       required>
            </div>

            <div class="form-group">
                <label>Correo</label>

                <input type="email"
                       name="correo"
                       value="<%= usuario.getCorreo() %>"
                       required>
            </div>

            <div class="form-group">
                <label>Teléfono</label>

                <input type="text"
                       name="telefono"
                       value="<%= usuario.getTelefono() == null ? "" : usuario.getTelefono() %>">
            </div>

            <div class="actions">

                <button type="submit"
                        class="btn btn-save">
                    Guardar cambios
                </button>

                <a href="<%= request.getContextPath() %>/perfil"
                   class="btn btn-cancel">
                    Cancelar
                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>