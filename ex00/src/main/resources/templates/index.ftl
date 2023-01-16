<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="en">
<head>
    <head>
        <title>Cinema</title>
        <style>
            a {
                padding: 10px 0;
                border-width: 0;
                border-radius: 1em;
                display: block;
                width: 500px;
                height: 30px;
                margin: auto;
                background: #60e6c5;
                color: black;
                font-size: 25px;
                outline: none;
                text-transform: uppercase;
                text-align: center;
            }
        </style>
    </head>
<body>
<footer>
    <div style="display: flex; flex-direction: row; justify-content: center; align-items: center; margin-top: 25px; margin-bottom: 25px">
        <a href="/index?lang=en" style="margin-right: 15px"> <@spring.message "lang.eng"/></a>
        <a href="/index?lang=ru"> <@spring.message "lang.ru"/></a>
    </div>
</footer>
<br>
<br>
<br>
<a href="/admin/panel/halls"><@spring.message "page.index.halls"/></a>
<a href="/admin/panel/films"><@spring.message "page.index.films"/></a>
<a href="/admin/panel/sessions"><@spring.message "page.index.sessions"/></a>
<a href="/session/search"><@spring.message "page.index.search"/></a>
</body>
</html>
