<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Successful registration</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Yeon Sung', cursive;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="border-collapse: collapse; border-radius: 5px;">
    <tr>
        <td bgcolor="#28a745" style="padding: 40px 0 30px 0;">
           <p style="margin-left: 100px;" ><img src="logo.png" width="100"/> Registration</p>
        </td>
    </tr>
    <tr>
        <td style="padding: 40px 30px 40px 30px;">
            <p>Dear <b>${name}</b>,</p>
            <p>Welcome to the bandaddict site!</p>
            <p>To user the site, please activate first your account: ${url}/activate/${token}</p>
            <p>If you have not registered on the site, please ignore this email!</p>
        </td>
    </tr>
    <tr>
        <td align="center" bgcolor="#28a745" style="padding: 40px 0 30px 0;">
            <p><b>bandAddict team!</b></p>
        </td>
    </tr>
</table>

</body>
</html>