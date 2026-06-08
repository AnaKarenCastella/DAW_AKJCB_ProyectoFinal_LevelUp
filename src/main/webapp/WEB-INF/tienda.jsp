<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Videojuego" %>
<%@ page import="com.app.model.Usuario" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>LevelUp - Tienda</title>

    <style>
        * {
            box-sizing: border-box;
        }

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
            width: auto;
            display: block;
        }

        .nav-links {
            display: flex;
            align-items: center;
            gap: 28px;
        }

        .nav-links a {
            color: #c7d5e0;
            text-decoration: none;
            font-size: 14px;
            font-weight: 700;
        }

        .nav-links a:hover,
        .nav-links a.active {
            color: #00bfff;
        }

        .hero {
            margin: 28px auto 0;
            width: calc(100% - 84px);
            min-height: 330px;
            border-radius: 12px;
            border: 1px solid rgba(0, 191, 255, 0.18);
            background: linear-gradient(135deg, #0b0f14, #121821, #1f2a37);
            display: flex;
            align-items: center;
            padding: 60px;
            box-shadow: 0 0 35px rgba(0, 191, 255, 0.08);
        }

        .hero h1 {
            margin: 0;
            font-size: 48px;
            line-height: 1.05;
            letter-spacing: 2px;
            text-transform: uppercase;
        }

        .hero h1 span {
            color: #00bfff;
        }

        .hero p {
            margin: 18px 0 28px;
            color: #b9c7d5;
            font-size: 18px;
        }

        .hero button {
            background: #00bfff;
            color: #061018;
            border: none;
            border-radius: 6px;
            padding: 13px 28px;
            font-weight: 800;
            cursor: pointer;
        }

        .store-bar {
            width: calc(100% - 84px);
            margin: 24px auto 0;
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.12);
            border-radius: 10px;
            padding: 14px 18px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 18px;
        }

        .section-title {
            color: #d6e2ee;
            font-size: 18px;
            letter-spacing: 1px;
            text-transform: uppercase;
            font-weight: 800;
        }

        .search-section form {
            display: flex;
            gap: 10px;
        }

        .search-section input {
            width: 420px;
            background: #0b0f14;
            color: #ffffff;
            padding: 12px 14px;
            border: 1px solid rgba(0, 191, 255, 0.18);
            border-radius: 6px;
            outline: none;
            font-size: 14px;
        }

        .search-section button {
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            background: #00bfff;
            color: #061018;
            font-weight: 800;
            cursor: pointer;
        }

        .contenedor {
            width: calc(100% - 84px);
            margin: 24px auto 60px;
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(235px, 1fr));
            gap: 18px;
        }

        .card {
            background: #121821;
            border-radius: 8px;
            overflow: hidden;
            border: 1px solid rgba(0, 191, 255, 0.10);
            box-shadow: 0 0 18px rgba(0, 0, 0, 0.45);
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 0 24px rgba(0, 191, 255, 0.18);
        }

        .card-link {
            color: inherit;
            text-decoration: none;
            display: block;
        }

        .card img {
            width: 100%;
            height: 145px;
            object-fit: cover;
            display: block;
        }

        .contenido {
            padding: 14px;
        }

        .titulo {
            font-size: 17px;
            color: #ffffff;
            margin-bottom: 8px;
            font-weight: 800;
            min-height: 42px;
        }

        .descripcion {
            color: #00bfff;
            line-height: 1.35;
            font-size: 13px;
            min-height: 36px;
            font-weight: 700;
        }

        .meta {
            margin-top: 8px;
            color: #9fb0c0;
            font-size: 12px;
            line-height: 1.3;
        }

        .card-bottom {
            padding: 0 14px 14px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .precio {
            color: #ffffff;
            font-size: 16px;
            font-weight: 800;
        }

        .card-bottom form {
            margin: 0;
        }

        .card-bottom button {
            width: 42px;
            height: 36px;
            border: none;
            border-radius: 6px;
            background: #06283a;
            color: #00bfff;
            font-size: 18px;
            font-weight: 800;
            cursor: pointer;
            border: 1px solid rgba(0, 191, 255, 0.20);
        }

        .card-bottom button:hover {
            background: #00bfff;
            color: #061018;
        }

        .benefits {
            width: calc(100% - 84px);
            margin: 0 auto 60px;
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 14px;
        }

        .benefit {
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.12);
            border-radius: 10px;
            padding: 18px;
            color: #c7d5e0;
        }

        .benefit strong {
            color: #00bfff;
            display: block;
            margin-bottom: 6px;
            text-transform: uppercase;
            font-size: 13px;
        }

        .empty {
            width: calc(100% - 84px);
            margin: 30px auto;
            color: #c7d5e0;
            font-size: 18px;
        }

        .suggestion-box{
            width: calc(100% - 84px);
            margin: 18px auto;
            padding: 15px;
            border-radius: 8px;

            background: rgba(0,191,255,0.08);
            border: 1px solid rgba(0,191,255,0.25);

            color: #c7d5e0;
            font-size: 15px;
        }

        .suggestion-box strong{
            color:#00bfff;
        }
    </style>
</head>

<body>

<header class="navbar">
    <div class="brand">
        <img src="<%= request.getContextPath() %>/assets/img/levelup-logo.png" alt="LevelUp Logo">
    </div>

    <nav class="nav-links">
        <a class="active" href="<%= request.getContextPath() %>/tienda">Tienda</a>
        <a href="#">Categorías</a>
        <a href="#">Ofertas</a>
        <a href="#">Novedades</a>

        <%
            Usuario usuarioSesion =
                    (Usuario) session.getAttribute("usuario");
        %>

        <% if (usuarioSesion != null) { %>

        <a href="<%= request.getContextPath() %>/perfil">
            <%= usuarioSesion.getNombre() %>
        </a>

        <% } else { %>

        <a href="<%= request.getContextPath() %>/login">
            Iniciar sesión
        </a>

        <% } %>

        <a href="<%= request.getContextPath() %>/carrito">
            Carrito
        </a>
    </nav>
</header>

<section class="hero">
    <div class="hero-content">
        <h1>Sube de nivel<br>Comienza tu próxima <span>aventura</span></h1>
        <p>Compra videojuegos, descubre ofertas y explora los nuevos lanzamientos.</p>
        <button>Explorar tienda</button>
    </div>
</section>

<section class="store-bar">
    <div class="section-title">Destacados</div>

    <div class="search-section">
        <form action="<%= request.getContextPath() %>/tienda" method="get">
            <input type="text"
                   name="busqueda"
                   placeholder="Buscar juegos..."
                   value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
            <button type="submit">Buscar</button>
        </form>
    </div>
</section>


<%
    String busquedaRealizada =
            (String) request.getAttribute("busqueda");

    String sugerencia =
            (String) request.getAttribute("sugerencia");

    if (sugerencia != null) {
%>


<div class="suggestion-box">
    Tal vez quisiste decir
    <strong><%= sugerencia %></strong>
    en lugar de
    <strong><%= busquedaRealizada %></strong>.
</div>


<%
    }
%>

<%
    List<Videojuego> lista = (List<Videojuego>) request.getAttribute("lista");

    if (lista != null && !lista.isEmpty()) {
%>

<div class="contenedor">

    <%
        for (Videojuego v : lista) {
    %>

    <div class="card">

        <a href="<%= request.getContextPath() %>/detalleJuego?id=<%= v.getRawgId() %>" class="card-link">
            <img src="<%= v.getImagenUrl() %>" alt="Imagen de videojuego">

            <div class="contenido">
                <div class="titulo"><%= v.getTitulo() %></div>

                <div class="descripcion">
                    <%= v.getGeneros() %>
                </div>

                <div class="meta">
                    Plataformas: <%= v.getPlataforma() %>
                </div>

                <div class="meta">
                    Rating: <%= v.getRating() %> / 5
                </div>

                <div class="meta">
                    Lanzamiento: <%= v.getFechaLanzamiento() %>
                </div>

                <div class="meta">
                    Stock: <%= v.getStock() %>
                </div>
            </div>
        </a>

        <div class="card-bottom">
            <div class="precio">$<%= v.getPrecio() %></div>

            <form action="<%= request.getContextPath() %>/carrito" method="post">
                <input type="hidden" name="accion" value="agregar">
                <input type="hidden" name="idJuego" value="<%= v.getRawgId() %>">
                <button type="submit">+</button>
            </form>
        </div>

    </div>

    <%
        }
    %>

</div>

<%
} else {
%>

<div class="empty">
    No hay videojuegos disponibles.
</div>

<%
    }
%>

<section class="benefits">
    <div class="benefit">
        <strong>Pagos seguros</strong>
        Tus compras protegidas durante todo el proceso.
    </div>

    <div class="benefit">
        <strong>Entrega digital</strong>
        Recibe tu videojuego de forma rápida y sencilla.
    </div>

    <div class="benefit">
        <strong>Soporte</strong>
        Atención para ayudarte con tus pedidos.
    </div>

    <div class="benefit">
        <strong>Comunidad</strong>
        Una tienda pensada para jugadores.
    </div>
</section>

</body>
</html>