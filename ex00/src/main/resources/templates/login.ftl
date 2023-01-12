<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Cinema</title>
    <style>
        form {
            /* Just to center the form on the page */
            margin: 200px auto 0;
            width: 400px;
            /* To see the outline of the form */
            padding: 1em;
            border: 1px solid #ccc;
            border-radius: 1em;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        form li + li {
            margin-top: 1em;
        }

        label {
            /* To make sure that all labels have the same size and are properly aligned */
            display: inline-block;
            width: 90px;
            text-align: right;
        }

        input {
            /* Убедимся, что все поля имеют одинаковые настройки шрифта
               По умолчанию в textarea используется моноширинный шрифт */
            font: 1em sans-serif;

            /* Определим размер полей */
            width: 300px;
            box-sizing: border-box;

            /* Стилизуем границы полей */
            border: 1px solid #999;
        }

        input:focus {
            /* Дополнительная подсветка для элементов в фокусе */
            border-color: #000;
        }

        button {
            padding: 10px 0;
            border-width: 0;
            display: block;
            width: 120px;
            margin: 25px auto 0;
            background: #60e6c5;
            color: white;
            font-size: 14px;
            outline: none;
            text-transform: uppercase;
        }

    </style>
</head>

<body>
<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

<#--    <div><label> User Name : <input type="text" name="username"/> </label></div>-->
<#--    <div><label> Password: <input type="password" name="password"/> </label></div>-->
<#--    <button type="submit">Login</button>-->

    <ul>
        <li>
            <label for="name">Name:</label>
            <input type="text" id="name" name="username" />
        </li>
        <li>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" />
        </li>
        <li class="button">
            <button type="submit">Login</button>
        </li>
    </ul>
</form>
</body>
</html>