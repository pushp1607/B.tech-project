<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Network Analysis Hub</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Poppins', sans-serif;
      background-color: #1e1e1e;
      margin: 0;
      padding: 0;
      color: #fff;
    }

    .container {
      max-width: 900px;
      margin: 50px auto;
      padding: 20px;
      background-color: #2d2d2d;
      border-radius: 20px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
      text-align: center;
    }

    h1, h2 {
      color: #F3C738;
      font-weight: 700;
    }

    h1 {
      font-size: 3em;
      margin-bottom: 30px;
    }

    h2 {
      font-size: 2em;
      margin-bottom: 20px;
    }

    .description {
      margin-bottom: 30px;
      font-size: 1.2em;
      line-height: 1.6;
      text-align: justify;
      color: #ccc;
    }

    .features {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
    }

    .feature {
      flex: 0 0 calc(33.333% - 40px);
      margin: 20px;
      padding: 30px 20px;
      background-color: #F3C738;
      color: #1e1e1e;
      text-decoration: none;
      border-radius: 20px;
      transition: transform 0.3s ease, background 0.3s ease;
      cursor: pointer;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
    }

    .feature:hover {
      background-color: #f0b500;
      transform: translateY(-5px);
    }

    .feature span {
      font-size: 1.2em;
    }

    .link {
      color: #F3C738;
      text-decoration: none;
      font-weight: 700;
      letter-spacing: 1px;
      font-size: 1.2em;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Network Analysis Hub</h1>
  <div class="description">
    <p>Empowering your network management with cutting-edge automation. Explore the features below to enhance efficiency and minimize downtime.</p>
  </div>
  <h2>Key Features:</h2>
  <div class="features">
    <a href="http://localhost/pingprocessor.html" class="feature">
      <span>Ping Processor</span>
    </a>
    <a href="http://localhost/jitterprocessor.html" class="feature">
      <span>Jitter Processor</span>
    </a>
    <a href="http://localhost/graph.html" class="feature">
      <span>Ping Destination (RTT Graph)</span>
    </a>
    <a href="http://localhost/analysisWindow.html" class="feature">
      <span>Analysis Window (RTT dataset) </span>
    </a>
    <a href="http://localhost/24.html" class="feature">
      <span>Hourly Network Traffic</span>
    </a>
    <a href="http://localhost/piechart.html" class="feature">
      <span>Types of Action over dataset</span>
    </a>
    <a href="http://localhost/action.html" class="feature">
      <span>Filter the Dataset on the basis of Action (Pass/Block)</span>
    </a>
    <a href="http://localhost/alertMail.html" class="feature">
      <span>Configure Alert Mail Settings</span>
    </a>
    <a href="http://localhost/urlGeoMappingALL.html" class="feature">
      <span>IP Address GeoLocation Mapping</span>
    </a>
    <a href="http://localhost/URLcategorizationAlertMail.html" class="feature">
      <span>URL Categorization & Recommendation System</span>
    </a>
  </div>
</div>
</body>
</html>
