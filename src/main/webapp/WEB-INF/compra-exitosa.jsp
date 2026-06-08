<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.app.model.Pedido" %>

<%
    Pedido pedido = (Pedido) request.getAttribute("pedido");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Compra Exitosa - LevelUp</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

    <style>

        .success-container{
            max-width:900px;
            margin:60px auto;
            padding:50px;
            text-align:center;
            background:#111c2e;
            border-radius:20px;
            border:1px solid #00bfff55;
        }

        .success-icon{
            font-size:80px;
            margin-bottom:20px;
        }

        .success-title{
            color:white;
            font-size:42px;
            margin-bottom:15px;
        }

        .success-text{
            color:#cfd8e3;
            font-size:18px;
            margin-bottom:40px;
        }

        .pedido-info{
            margin:30px 0;
            color:white;
            font-size:22px;
            line-height:2;
        }

        .success-buttons{
            display:flex;
            justify-content:center;
            gap:20px;
            margin-top:40px;
        }

        .btn-success{
            padding:14px 30px;
            border:none;
            border-radius:10px;
            text-decoration:none;
            font-weight:bold;
        }

        .btn-primary{
            background:#00bfff;
            color:black;
        }

        .btn-secondary{
            background:#1f2937;
            color:white;
        }

    </style>

</head>

<body>

<div class="success-container">

    <h1 class="success-title">
        ¡Compra realizada con éxito!
    </h1>

    <p class="success-text">
        Gracias por tu compra en LevelUp.
    </p>

    <div class="pedido-info">

        <div>
            Pedido #<%= pedido.getIdPedido() %>
        </div>

        <div>
            Estado:
            <strong>
                <%= pedido.getEstado() %>
            </strong>
        </div>

        <div>
            Total:
            <strong>
                $<%= pedido.getTotal() %>
            </strong>
        </div>

    </div>

    <div class="success-buttons">

        <a class="btn-success btn-primary"
           href="<%= request.getContextPath() %>/historialCompras">
            Ver historial
        </a>

        <a class="btn-success btn-secondary"
           href="<%= request.getContextPath() %>/tienda">
            Seguir comprando
        </a>

    </div>

</div>

</body>
</html>