<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro - LevelUp</title>

    <style>

        *{
            box-sizing:border-box;
        }

        body{
            margin:0;
            font-family:Arial, Helvetica, sans-serif;
            background:#0b0f14;
            color:white;
            min-height:100vh;
            display:flex;
            justify-content:center;
            align-items:center;
        }

        .register-container{
            width:450px;
            background:#121821;
            border:1px solid rgba(0,191,255,.20);
            border-radius:14px;
            padding:35px;
            box-shadow:0 0 30px rgba(0,191,255,.08);
        }

        .logo{
            text-align:center;
            margin-bottom:25px;
        }

        .logo img{
            width:220px;
            height:auto;
        }

        h1{
            text-align:center;
            margin-bottom:25px;
            font-size:28px;
            letter-spacing:1px;
        }

        .form-group{
            margin-bottom:18px;
        }

        label{
            display:block;
            margin-bottom:6px;
            color:#9aa7b4;
            font-size:13px;
            text-transform:uppercase;
            font-weight:bold;
        }

        input{
            width:100%;
            padding:12px;
            background:#0b0f14;
            border:1px solid rgba(0,191,255,.20);
            border-radius:8px;
            color:white;
            outline:none;
        }

        input:focus{
            border-color:#00bfff;
        }

        .btn-register{
            width:100%;
            border:none;
            background:#00bfff;
            color:#061018;
            padding:14px;
            border-radius:8px;
            font-weight:bold;
            cursor:pointer;
            margin-top:10px;
        }

        .btn-register:hover{
            background:#33ccff;
        }

        .error{
            background:#5a1e27;
            color:white;
            padding:12px;
            border-radius:8px;
            margin-bottom:20px;
        }

        .login-link{
            text-align:center;
            margin-top:20px;
        }

        .login-link a{
            color:#00bfff;
            text-decoration:none;
        }

        .login-link a:hover{
            text-decoration:underline;
        }

    </style>

</head>

<body>

<div class="register-container">

    <div class="logo">
        <img src="<%= request.getContextPath() %>/assets/img/levelup-logo.png"
             alt="LevelUp">
    </div>

    <h1>Crear Cuenta</h1>

    <% if(error != null){ %>
    <div class="error">
        <%= error %>
    </div>
    <% } %>

    <form action="<%= request.getContextPath() %>/registro"
          method="post"
          autocomplete="off">

        <div class="form-group">
            <label>Nombre</label>
            <input type="text"
                   name="nombre"
                   required>
        </div>

        <div class="form-group">
            <label>Correo</label>
            <input type="email"
                   name="correo"
                   autocomplete="off"
                   required>
        </div>

        <div class="form-group">
            <label>Contraseña</label>
            <input type="password"
                   name="password"
                   autocomplete="new-password"
                   required>
        </div>

        <div class="form-group">
            <label>Teléfono</label>
            <input type="text"
                   name="telefono">
        </div>

        <button type="submit"
                class="btn-register">
            Crear Cuenta
        </button>

    </form>

    <div class="login-link">
        ¿Ya tienes cuenta?
        <a href="<%= request.getContextPath() %>/login">
            Inicia sesión aquí
        </a>
    </div>

</div>

</body>
</html>