<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String error = (String) request.getAttribute("error");
    String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - LevelUp</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: #0b0f14;
            color: #ffffff;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            width: 460px;
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.22);
            border-radius: 14px;
            padding: 38px;
            box-shadow: 0 0 32px rgba(0, 191, 255, 0.08);
        }

        .logo {
            text-align: center;
            margin-bottom: 28px;
        }

        .logo img {
            width: 230px;
            height: auto;
        }

        h1 {
            text-align: center;
            margin: 0 0 28px;
            font-size: 30px;
            letter-spacing: 1px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            color: #9aa7b4;
            font-size: 13px;
            text-transform: uppercase;
            font-weight: bold;
            margin-bottom: 7px;
        }

        input {
            width: 100%;
            padding: 13px;
            border-radius: 8px;
            border: 1px solid rgba(0, 191, 255, 0.22);
            background: #0b0f14;
            color: white;
            outline: none;
            font-size: 15px;
        }

        input:focus {
            border-color: #00bfff;
        }

        .btn-login {
            width: 100%;
            padding: 14px;
            border: none;
            border-radius: 8px;
            background: #00bfff;
            color: #061018;
            font-weight: bold;
            cursor: pointer;
            margin-top: 8px;
            font-size: 15px;
        }

        .btn-login:hover {
            background: #33ccff;
        }

        .error {
            background: #5a1e27;
            color: #ffffff;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 18px;
            text-align: center;
        }

        .success {
            background: rgba(46, 255, 163, 0.12);
            color: #2effa3;
            border: 1px solid rgba(46, 255, 163, 0.22);
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 18px;
            text-align: center;
        }

        .register-link {
            text-align: center;
            margin-top: 22px;
            color: #c7d5e0;
        }

        .register-link a {
            color: #00bfff;
            text-decoration: none;
            font-weight: bold;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<div class="login-container">

    <div class="logo">
        <img src="<%= request.getContextPath() %>/assets/img/levelup-logo.png" alt="LevelUp">
    </div>

    <h1>Iniciar Sesión</h1>

    <% if (mensaje != null) { %>
    <div class="success"><%= mensaje %></div>
    <% } %>

    <% if (error != null) { %>
    <div class="error"><%= error %></div>
    <% } %>


    <form action="<%= request.getContextPath() %>/login"
          method="post"
          autocomplete="off">

        <div class="form-group">
            <label>CORREO</label>
            <input type="email"
                   name="correo"
                   autocomplete="off"
                   required>
        </div>

        <div class="form-group">
            <label>CONTRASEÑA</label>
            <input type="password"
                   name="password"
                   autocomplete="off"
                   required>
        </div>

        <button type="submit" class="btn-login">
            Iniciar sesión
        </button>

    </form>

    <div class="register-link">
        ¿No tienes cuenta?
        <a href="<%= request.getContextPath() %>/registro">
            Crear cuenta
        </a>
    </div>

</div>

</body>
</html>