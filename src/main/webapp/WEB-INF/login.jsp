<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>LevelUp - Login</title>
    <style>
        body {
            font-family: Arial;
            background: linear-gradient(135deg, #1a1a2e, #16213e);
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background: #0f3460;
            padding: 30px;
            border-radius: 15px;
            width: 300px;
            box-shadow: 0px 0px 20px rgba(0,0,0,0.5);
        }

        h2 {
            text-align: center;
        }

        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 8px;
        }

        button {
            width: 100%;
            padding: 10px;
            background: #e94560;
            border: none;
            border-radius: 8px;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background: #ff2e63;
        }

        .error {
            color: #ff4d4d;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>LevelUp</h2>

    <form action="auth" method="post">
        <input type="hidden" name="accion" value="login">

        <input type="text" name="correo" placeholder="Correo" required>
        <input type="password" name="password" placeholder="Contraseña" required>

        <button type="submit">Iniciar sesión</button>
    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="error"><%= error %></div>
    <%
        }
    %>
</div>

</body>
</html>