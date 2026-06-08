<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Carrito" %>
<%@ page import="com.app.model.DetalleCarrito" %>
<%@ page import="com.app.model.Videojuego" %>

<%
    Carrito carrito = (Carrito) request.getAttribute("carrito");
    List<DetalleCarrito> detalles =
            (List<DetalleCarrito>) request.getAttribute("detalles");

    List<Videojuego> juegosCarrito =
            (List<Videojuego>) request.getAttribute("juegosCarrito");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito - LevelUp</title>

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
            font-size: 14px;
            font-weight: 700;
        }

        .nav-links a:hover {
            color: #00bfff;
        }

        .container {
            width: calc(100% - 84px);
            margin: 35px auto 70px;
        }

        .title {
            font-size: 36px;
            text-transform: uppercase;
            margin-bottom: 25px;
        }

        .cart-box {
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.14);
            border-radius: 12px;
            padding: 24px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            text-align: left;
            color: #00bfff;
            padding: 14px;
            border-bottom: 1px solid rgba(199, 213, 224, 0.12);
            text-transform: uppercase;
            font-size: 13px;
        }

        td {
            padding: 14px;
            border-bottom: 1px solid rgba(199, 213, 224, 0.08);
            color: #c7d5e0;
        }

        .total-box {
            margin-top: 24px;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            gap: 25px;
        }

        .total {
            font-size: 26px;
            font-weight: 900;
            color: #ffffff;
        }

        .btn-primary,
        .btn-danger,
        .btn-secondary {
            border: none;
            border-radius: 8px;
            padding: 12px 18px;
            font-weight: 800;
            cursor: pointer;
        }

        .btn-primary {
            background: #00bfff;
            color: #061018;
        }

        .btn-secondary {
            background: #06283a;
            color: #00bfff;
            border: 1px solid rgba(0, 191, 255, 0.28);
            text-decoration: none;
        }

        .btn-danger {
            background: #7a1f2b;
            color: #ffffff;
        }

        .actions {
            display: flex;
            gap: 12px;
            justify-content: flex-end;
            margin-top: 25px;
        }

        .empty {
            color: #c7d5e0;
            font-size: 18px;
            padding: 25px 0;
        }
    </style>
</head>

<body>

<header class="navbar">
    <div class="brand">
        <img src="<%= request.getContextPath() %>/assets/img/levelup-logo.png" alt="LevelUp Logo">
    </div>

    <nav class="nav-links">
        <a href="<%= request.getContextPath() %>/tienda">Tienda</a>
        <a href="<%= request.getContextPath() %>/carrito">Carrito</a>
        <a href="<%= request.getContextPath() %>/login">Iniciar sesión</a>
    </nav>
</header>

<div class="container">

    <h1 class="title">Carrito</h1>

    <div class="cart-box">

        <%
            if (detalles != null && !detalles.isEmpty()) {
        %>

        <table>
            <thead>
            <tr>
                <th>Imagen</th>
                <th>Videojuego</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Acción</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (Videojuego juego : juegosCarrito) {
            %>

            <tr>
                <td>
                    <img src="<%= juego.getImagenUrl() %>" style="width:120px; border-radius:8px;">
                </td>

                <td><%= juego.getTitulo() %></td>

                <td><%= juego.getStock() %></td>

                <td>$<%= juego.getPrecio() %></td>

                <td>
                    <form action="<%= request.getContextPath() %>/carrito" method="post">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="idDetalleCarrito" value="<%= juego.getIdJuego() %>">
                        <button type="submit" class="btn-danger">Eliminar</button>
                    </form>
                </td>
            </tr>

            <%
                }
            %>

            </tbody>
        </table>

        <div class="total-box">
            <span>Total:</span>
            <span class="total">$<%= carrito != null ? carrito.getTotal() : 0 %></span>
        </div>

        <div class="actions">
            <form action="<%= request.getContextPath() %>/carrito" method="post">
                <input type="hidden" name="accion" value="vaciar">
                <button type="submit" class="btn-danger">Vaciar carrito</button>
            </form>

            <button class="btn-primary">Continuar compra</button>
        </div>

        <%
        } else {
        %>

        <div class="empty">
            Tu carrito está vacío.
        </div>

        <div class="actions">
            <a class="btn-secondary" href="<%= request.getContextPath() %>/tienda">
                Volver a la tienda
            </a>
        </div>

        <%
            }
        %>

    </div>

</div>

</body>
</html>