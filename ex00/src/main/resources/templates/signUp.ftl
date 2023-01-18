<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html>
<head>
    <meta charset="utf-8" />
    <title>Cinema</title>
    <style>
        form {
            margin: 200px auto 0;
            width: 600px;
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

        input::placeholder{
            color:  red;
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
<form action="/signUp" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <ul>
        <li>
            <label for="firstName"><@spring.message "page.login.firstName"/></label>
            <#if error??>
                <input type="text" id="firstName" name="firstName"
                       placeholder="<@spring.message "errors.incorrect.firstName"/>"/>
            <#else>
                <input type="text" id="firstName" name="firstName"/>
            </#if>
        </li>
        <li>
            <label for="lastName"><@spring.message "page.login.lastName"/></label>
            <#if error??>
                <input type="text" id="lastName" name="lastName"
                       placeholder="<@spring.message "errors.incorrect.lastName"/>"/>
            <#else>
                <input type="text" id="lastName" name="lastName"/>
            </#if>
        </li>
        <li>
            <label for="email"><@spring.message "page.login.email"/></label>
            <#if error??>
                <input type="text" id="email" name="email"
                       placeholder="<@spring.message "errors.incorrect.email"/>"/>
            <#else>
                <input type="text" id="email" name="email"/>
            </#if>
        </li>
        <li>
            <label for="phoneNumber"><@spring.message "page.login.phone"/></label>
            <#if error??>
                <input type="text" id="phoneNumber" name="phoneNumber"
                       placeholder="<@spring.message "errors.incorrect.phone"/>"/>
            <#else>
                <input type="text" id="phoneNumber" name="phoneNumber"/>
            </#if>
        </li>
        <li>
            <label for="password"><@spring.message "page.login.password"/></label>
            <#if error??>
                <input type="text" id="password" name="password"
                       placeholder="<@spring.message "errors.incorrect.password"/>"/>
            <#else>
                <input type="text" id="password" name="password"/>
            </#if>
        </li>
        <li class="button">
            <button type="submit"><@spring.message "page.signup.register"/></button>
        </li>
    </ul>
</form>
</body>
<footer>
    <div style="display: flex; flex-direction: row; justify-content: center; align-items: center; margin-top: 25px; margin-bottom: 25px">
        <a href="/signUp?lang=en" style="margin-right: 15px"> <@spring.message "lang.eng"/></a>
        <a href="/signUp?lang=ru"> <@spring.message "lang.ru"/></a>
    </div>
</footer>
</html>