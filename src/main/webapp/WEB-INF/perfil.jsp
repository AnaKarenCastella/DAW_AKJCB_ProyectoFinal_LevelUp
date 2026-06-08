<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.app.model.Usuario" %>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi perfil - LevelUp</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: #0b0f14;
            color: #ffffff;
        }

        .navbar {
            height: 78px;
            background: #0b0f14;
            border-bottom: 1px solid rgba(0, 191, 255, 0.20);
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 42px;
        }

        .brand img {
            height: 44px;
        }

        .nav-links {
            display: flex;
            gap: 28px;
        }

        .nav-links a {
            color: #c7d5e0;
            text-decoration: none;
            font-weight: 700;
        }

        .nav-links a:hover {
            color: #00bfff;
        }

        .profile-container {
            width: calc(100% - 120px);
            max-width: 1000px;
            margin: 50px auto;
        }

        h1 {
            font-size: 42px;
            margin-bottom: 30px;
        }

        .profile-card {
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.18);
            border-radius: 14px;
            padding: 32px;
            display: grid;
            grid-template-columns: 180px 1fr;
            gap: 32px;
        }

        .avatar {
            width: 140px;
            height: 140px;
            border-radius: 50%;
            background: #00bfff;
            color: #061018;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 52px;
            font-weight: 900;
        }

        .info-row {
            border-bottom: 1px solid rgba(199, 213, 224, 0.10);
            padding: 16px 0;
        }

        .label {
            color: #8fa1b3;
            font-size: 13px;
            text-transform: uppercase;
            font-weight: 800;
            margin-bottom: 6px;
        }

        .value {
            font-size: 18px;
            color: #ffffff;
        }

        .actions {
            margin-top: 30px;
            display: flex;
            gap: 14px;
        }

        .btn {
            padding: 13px 20px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 800;
        }

        .btn-primary {
            background: #00bfff;
            color: #061018;
        }

        .btn-secondary {
            background: #06283a;
            color: #00bfff;
            border: 1px solid rgba(0, 191, 255, 0.28);
        }

        .btn-danger {
            background: #7a1f2b;
            color: white;
        }
    </style>
</head>

<body>

<header class="navbar">
    <div class="brand">
        <img src="<%= request.getContextPath() %>/assets/img/levelup-logo.png" alt="LevelUp">
    </div>

    <nav class="nav-links">
        <a href="<%= request.getContextPath() %>/tienda">Tienda</a>
        <a href="<%= request.getContextPath() %>/carrito">Carrito</a>
        <a href="<%= request.getContextPath() %>/perfil"><%= usuario.getNombre() %></a>
    </nav>
</header>

<div class="profile-container">

    <h1>Mi perfil</h1>

    <div class="profile-card">

        <div class="avatar">
            <%= usuario.getNombre().substring(0, 1).toUpperCase() %>
        </div>

        <div>
            <div class="info-row">
                <div class="label">Nombre</div>
                <div class="value"><%= usuario.getNombre() %></div>
            </div>

            <div class="info-row">
                <div class="label">Correo electrónico</div>
                <div class="value"><%= usuario.getCorreo() %></div>
            </div>

            <div class="info-row">
                <div class="label">Teléfono</div>
                <div class="value"><%= usuario.getTelefono() != null ? usuario.getTelefono() : "No registrado" %></div>
            </div>

            <div class="actions">
                <a class="btn btn-primary"
                   href="<%= request.getContextPath() %>/editarPerfil">
                    Editar perfil
                </a>

                <a class="btn btn-secondary"
                   href="<%= request.getContextPath() %>/historialCompras">
                    Historial de compras
                </a>

                <a class="btn btn-danger" href="<%= request.getContextPath() %>/logout">Cerrar sesión</a>
            </div>
        </div>

    </div>

</div>


</body>
</html>