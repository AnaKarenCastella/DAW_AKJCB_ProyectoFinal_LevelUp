<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Videojuego" %>

<!DOCTYPE html>
<html>
<head>
    <title>LevelUp - Tienda</title>
    <style>
        body {
            font-family: Arial;
            background: #1a1a2e;
            color: white;
        }

        header {
            background: #0f3460;
            padding: 15px;
            text-align: center;
            font-size: 24px;
        }

        .busqueda {
            text-align: center;
            margin: 20px;
        }

        input {
            padding: 10px;
            width: 300px;
            border-radius: 8px;
            border: none;
        }

        button {
            padding: 10px;
            border-radius: 8px;
            border: none;
            background: #e94560;
            color: white;
            cursor: pointer;
        }

        .contenedor {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            padding: 20px;
        }

        .card {
            background: #16213e;
            padding: 15px;
            border-radius: 12px;
            text-align: center;
        }

        .card img {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 10px;
        }

        .precio {
            color: #00ffcc;
            font-weight: bold;
        }
    </style>
</head>
<body>

<header>LevelUp Store</header>

<div class="busqueda">
    <form action="videojuegos" method="get">
        <input type="text" name="busqueda" placeholder="Buscar juego...">
        <button type="submit">Buscar</button>
    </form>
</div>

<div class="contenedor">

    <%
        List<Videojuego> lista = (List<Videojuego>) request.getAttribute("lista");

        if (lista != null) {
            for (Videojuego v : lista) {
    %>

    <div class="card">
        <h3><%= v.getTitulo() %></h3>
        <p><%= v.getDescripcion() %></p>
        <p class="precio">$<%= v.getPrecio() %></p>

        <form action="carrito" method="post">
            <input type="hidden" name="accion" value="agregar">
            <input type="hidden" name="idJuego" value="<%= v.getIdJuego() %>">
            <button type="submit">Agregar al carrito</button>
        </form>
    </div>

    <%
        }
    } else {
    %>
    <p style="text-align:center;">No hay videojuegos disponibles</p>
    <%
        }
    %>

</div>

</body>
</html>