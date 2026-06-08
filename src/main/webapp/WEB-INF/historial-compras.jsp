<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Pedido" %>
<%@ page import="com.app.model.Usuario" %>

<%
    List<Pedido> pedidos =
            (List<Pedido>) request.getAttribute("pedidos");

    Usuario usuarioSesion =
            (Usuario) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Historial de compras - LevelUp</title>

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

        .container {
            width: calc(100% - 120px);
            max-width: 1100px;
            margin: 45px auto;
        }

        h1 {
            font-size: 40px;
            text-transform: uppercase;
            margin-bottom: 28px;
        }

        .history-card {
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.16);
            border-radius: 14px;
            padding: 26px;
            margin-bottom: 18px;
        }

        .order-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 18px;
        }

        .order-id {
            font-size: 22px;
            font-weight: 900;
            color: #ffffff;
        }

        .status {
            padding: 8px 14px;
            border-radius: 20px;
            background: rgba(0, 191, 255, 0.12);
            color: #00bfff;
            font-weight: 800;
            font-size: 13px;
        }

        .order-info {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 16px;
            color: #c7d5e0;
        }

        .label {
            color: #8fa1b3;
            font-size: 12px;
            text-transform: uppercase;
            font-weight: 800;
            margin-bottom: 5px;
        }

        .value {
            color: #ffffff;
            font-size: 17px;
        }

        .empty {
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.16);
            border-radius: 14px;
            padding: 35px;
            color: #c7d5e0;
            font-size: 18px;
        }

        .actions {
            margin-top: 24px;
        }

        .btn {
            display: inline-block;
            padding: 12px 18px;
            border-radius: 8px;
            background: #00bfff;
            color: #061018;
            text-decoration: none;
            font-weight: 900;
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

        <% if (usuarioSesion != null) { %>
        <a href="<%= request.getContextPath() %>/perfil">
            <%= usuarioSesion.getNombre() %>
        </a>
        <% } else { %>
        <a href="<%= request.getContextPath() %>/login">
            Iniciar sesión
        </a>
        <% } %>
    </nav>
</header>

<div class="container">

    <h1>Historial de compras</h1>

    <%
        if (pedidos != null && !pedidos.isEmpty()) {
            for (Pedido p : pedidos) {
    %>

    <div class="history-card">

        <div class="order-header">
            <div class="order-id">
                Pedido #<%= p.getIdPedido() %>
            </div>

            <div class="status">
                <%= p.getEstado() %>
            </div>
        </div>

        <div class="order-info">
            <div>
                <div class="label">Fecha</div>
                <div class="value"><%= p.getFecha() %></div>
            </div>

            <div>
                <div class="label">Total</div>
                <div class="value">$<%= p.getTotal() %></div>
            </div>

            <div>
                <div class="label">Tipo de entrega</div>
                <div class="value">Digital</div>
            </div>
        </div>

    </div>

    <%
        }
    } else {
    %>

    <div class="empty">
        Todavía no tienes compras registradas.

        <div class="actions">
            <a class="btn" href="<%= request.getContextPath() %>/tienda">
                Ir a la tienda
            </a>
        </div>
    </div>

    <%
        }
    %>

</div>

</body>
</html>