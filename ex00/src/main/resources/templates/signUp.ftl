<html>
<head>
    <meta charset="utf-8" />
    <title>Cinema</title>
    <style>
        form {
            margin: 200px auto 0;
            width: 450px;
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
            display: inline-block;
            width: 140px;
            text-align: right;
        }

        input {
            font: 1em sans-serif;
            width: 300px;
            box-sizing: border-box;
            border: 1px solid #999;
        }

        input:focus {
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
<form action="/signUp/${film_id}" method="post">
    <ul>
        <li>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" />
        </li>
        <li>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" />
        </li>
        <li class="button">
            <button type="submit">Register</button>
        </li>
    </ul>
</form>
</body>
</html>