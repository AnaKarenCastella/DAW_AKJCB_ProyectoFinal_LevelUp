<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.app.model.Videojuego" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Resena" %>
<%@ page import="com.app.model.Usuario" %>

<%
    Videojuego juego = (Videojuego) request.getAttribute("juego");

    if (juego == null) {
        response.sendRedirect(request.getContextPath() + "/tienda");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= juego.getTitulo() %> - LevelUp</title>

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

        .nav-links a:hover {
            color: #00bfff;
        }

        .hero-detail {
            width: calc(100% - 84px);
            margin: 28px auto 0;
            border-radius: 14px;
            overflow: hidden;
            border: 1px solid rgba(0, 191, 255, 0.18);
            background: #121821;
            box-shadow: 0 0 35px rgba(0, 191, 255, 0.08);
        }

        .hero-image {
            min-height: 420px;
            background:
                    linear-gradient(
                            90deg,
                            rgba(11, 15, 20, 0.96),
                            rgba(11, 15, 20, 0.72),
                            rgba(11, 15, 20, 0.25)
                    ),
                    url("<%= juego.getImagenUrl() %>");
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: flex-end;
            padding: 50px;
        }

        .hero-content {
            max-width: 720px;
        }

        .hero-content h1 {
            margin: 0;
            font-size: 52px;
            line-height: 1.05;
            text-transform: uppercase;
            letter-spacing: 1.5px;
        }

        .hero-content .genres {
            margin-top: 14px;
            color: #00bfff;
            font-size: 15px;
            font-weight: 800;
            letter-spacing: 0.5px;
        }

        .detail-layout {
            width: calc(100% - 84px);
            margin: 26px auto 60px;
            display: grid;
            grid-template-columns: 1.8fr 0.8fr;
            gap: 24px;
        }

        .panel {
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.12);
            border-radius: 12px;
            padding: 26px;
            box-shadow: 0 0 18px rgba(0, 0, 0, 0.40);
        }

        .panel h2 {
            margin: 0 0 18px;
            font-size: 22px;
            color: #ffffff;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .description {
            color: #c7d5e0;
            line-height: 1.7;
            font-size: 15px;
            text-align: justify;
        }

        .info-row {
            border-bottom: 1px solid rgba(199, 213, 224, 0.10);
            padding: 13px 0;
        }

        .info-row:last-child {
            border-bottom: none;
        }

        .label {
            color: #7f91a3;
            font-size: 12px;
            text-transform: uppercase;
            margin-bottom: 5px;
            font-weight: 800;
        }

        .value {
            color: #ffffff;
            font-size: 15px;
            line-height: 1.4;
        }

        .price-box {
            margin-top: 22px;
            padding: 18px;
            background: #0b0f14;
            border: 1px solid rgba(0, 191, 255, 0.16);
            border-radius: 10px;
        }

        .price {
            font-size: 32px;
            font-weight: 900;
            color: #ffffff;
            margin-bottom: 14px;
        }

        .actions {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        .btn-primary,
        .btn-secondary {
            width: 100%;
            padding: 14px;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 900;
            border: none;
        }

        .btn-primary {
            background: #00bfff;
            color: #061018;
        }

        .btn-primary:hover {
            background: #33ccff;
        }

        .btn-secondary {
            background: #06283a;
            color: #00bfff;
            border: 1px solid rgba(0, 191, 255, 0.28);
        }

        .btn-secondary:hover {
            background: rgba(0, 191, 255, 0.12);
        }

        .back-link {
            width: calc(100% - 84px);
            margin: 24px auto 0;
        }

        .back-link a {
            color: #00bfff;
            text-decoration: none;
            font-weight: 800;
        }

        .back-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 900px) {
            .detail-layout {
                grid-template-columns: 1fr;
            }

            .hero-content h1 {
                font-size: 36px;
            }

            .hero-image {
                min-height: 330px;
                padding: 30px;
            }

            .hero-detail,
            .detail-layout,
            .back-link {
                width: calc(100% - 32px);
            }
        }

        <%-- Este bloque es para mostrar las reseñas --%>
        .reviews-section {
            width: calc(100% - 84px);
            margin: 0 auto 70px;
            display: grid;
            grid-template-columns: 0.8fr 1.2fr;
            gap: 24px;
        }

        .reviews-header {
            grid-column: 1 / -1;
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.12);
            border-radius: 12px;
            padding: 22px 26px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .reviews-header h2 {
            margin: 0;
            color: #ffffff;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .average-rating {
            color: #00bfff;
            font-weight: 900;
            font-size: 18px;
        }

        .review-form-box,
        .review-list {
            background: #121821;
            border: 1px solid rgba(0, 191, 255, 0.12);
            border-radius: 12px;
            padding: 24px;
        }

        .review-form-box h3 {
            margin-top: 0;
            color: #ffffff;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            color: #7f91a3;
            font-size: 12px;
            text-transform: uppercase;
            margin-bottom: 6px;
            font-weight: 800;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            background: #0b0f14;
            color: #ffffff;
            border: 1px solid rgba(0, 191, 255, 0.18);
            border-radius: 8px;
            padding: 12px;
            outline: none;
            font-size: 14px;
        }

        .form-group textarea {
            resize: vertical;
        }

        .review-list {
            display: flex;
            flex-direction: column;
            gap: 16px;
        }

        .review-card {
            background: #0b0f14;
            border: 1px solid rgba(0, 191, 255, 0.10);
            border-radius: 10px;
            padding: 18px;
        }

        .review-top {
            display: flex;
            justify-content: space-between;
            color: #ffffff;
            margin-bottom: 10px;
        }

        .review-top span {
            color: #00bfff;
            font-weight: 900;
        }

        .review-card p {
            color: #c7d5e0;
            line-height: 1.5;
        }

        .review-date {
            color: #7f91a3;
            font-size: 12px;
            margin-top: 10px;
        }

        .empty-reviews {
            color: #c7d5e0;
        }

        @media (max-width: 900px) {
            .reviews-section {
                width: calc(100% - 32px);
                grid-template-columns: 1fr;
            }

            .reviews-header {
                flex-direction: column;
                align-items: flex-start;
                gap: 10px;
            }
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

        <a href="#">Carrito</a>
    </nav>
</header>

<div class="back-link">
    <a href="<%= request.getContextPath() %>/tienda">Volver a la tienda</a>
</div>

<section class="hero-detail">
    <div class="hero-image">
        <div class="hero-content">
            <h1><%= juego.getTitulo() %></h1>
            <div class="genres">
                <%= juego.getGeneros() %>
            </div>
        </div>
    </div>
</section>

<main class="detail-layout">

    <section class="panel">
        <h2>Sinopsis</h2>

        <div class="description">
            <%= juego.getDescripcion() %>
        </div>
    </section>

    <aside class="panel">
        <h2>Información</h2>

        <div class="info-row">
            <div class="label">Rating</div>
            <div class="value"><%= juego.getRating() %> / 5</div>
        </div>

        <div class="info-row">
            <div class="label">Lanzamiento</div>
            <div class="value"><%= juego.getFechaLanzamiento() %></div>
        </div>

        <div class="info-row">
            <div class="label">Plataformas</div>
            <div class="value"><%= juego.getPlataforma() %></div>
        </div>

        <div class="info-row">
            <div class="label">Desarrollador</div>
            <div class="value"><%= juego.getDesarrollador() %></div>
        </div>

        <div class="info-row">
            <div class="label">Publisher</div>
            <div class="value"><%= juego.getPublisher() %></div>
        </div>

        <div class="info-row">
            <div class="label">Sitio web</div>
            <div class="value"><%= juego.getSitioWeb() %></div>
        </div>

        <div class="price-box">
            <div class="price">$<%= juego.getPrecio() %></div>

            <div class="actions">
                <form action="<%= request.getContextPath() %>/carrito" method="post">
                    <input type="hidden" name="accion" value="agregar">
                    <input type="hidden" name="idJuego" value="<%= juego.getRawgId() %>">
                    <button type="submit" class="btn-primary">Agregar al carrito</button>
                </form>

                <form action="#" method="post">
                    <button type="submit" class="btn-secondary">Guardar en wishlist</button>
                </form>
            </div>
        </div>
    </aside>

</main>

<%
    List<Resena> resenas =
            (List<Resena>) request.getAttribute("resenas");

    Double promedioResenas =
            (Double) request.getAttribute("promedioResenas");

    if (promedioResenas == null) {
        promedioResenas = 0.0;
    }
%>

<section class="reviews-section">

    <div class="reviews-header">
        <h2>Reseñas de usuarios</h2>

        <div class="average-rating">
            Promedio: <%= String.format("%.1f", promedioResenas) %> / 5
        </div>
    </div>

    <div class="review-form-box">

        <h3>Escribe tu reseña</h3>

        <form action="<%= request.getContextPath() %>/guardarResena" method="post">

            <input type="hidden" name="rawgId" value="<%= juego.getRawgId() %>">

            <div class="form-group">
                <label>Nombre</label>
                <input type="text" name="nombreUsuario" placeholder="Tu nombre" required>
            </div>

            <div class="form-group">
                <label>Calificación</label>
                <select name="calificacion" required>
                    <option value="5">5 - Excelente</option>
                    <option value="4">4 - Muy bueno</option>
                    <option value="3">3 - Bueno</option>
                    <option value="2">2 - Regular</option>
                    <option value="1">1 - Malo</option>
                </select>
            </div>

            <div class="form-group">
                <label>Comentario</label>
                <textarea
                        name="comentario"
                        rows="4"
                        placeholder="Escribe tu opinión sobre el videojuego..."
                        required></textarea>
            </div>

            <button type="submit" class="btn-primary">
                Publicar reseña
            </button>

        </form>

    </div>

    <div class="review-list">

        <%
            if (resenas != null && !resenas.isEmpty()) {

                for (Resena r : resenas) {
        %>

        <div class="review-card">

            <div class="review-top">
                <strong><%= r.getNombreUsuario() %></strong>

                <span>
                    <%= r.getCalificacion() %> / 5
                </span>
            </div>

            <p>
                <%= r.getComentario() %>
            </p>

            <div class="review-date">
                <%= r.getFecha() %>
            </div>

        </div>

        <%
            }

        } else {
        %>

        <div class="empty-reviews">
            Aún no hay reseñas para este videojuego.
        </div>

        <%
            }
        %>

    </div>

</section>

</body>
</html>