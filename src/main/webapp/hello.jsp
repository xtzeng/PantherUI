<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/PantherUI.min.css">
<style>
#container1 {
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
</style>

<script src="js/PantherUI.min.js"></script>

<title>Apply Carousel</title>
</head>
<body>

<section class="panther-Carousels">
    <section id="container1" class="panther-Carousel-container">
        <div id="carousel1" class="panther-Carousel-carousel">
            <figure>
                <div class="panther-Carousel-panelTitleBar"><span class="panther-Carousel-panelTitle">&lt; beyond  &gt;</span></div>
                <div class="panther-Carousel-panelContent">
                    <!-- Panel 1: Add your content here -->
                    <ul>
					<li>Coffee</li>
					<li>Milk</li>
					</ul>
                </div>
            </figure>
            <figure>
                <div class="panther-Carousel-panelTitleBar"><span class="panther-Carousel-panelTitle">&lt; 饮料 &gt;</span></div>
                <div class="panther-Carousel-panelContent">
                    <!-- Panel 2: Add your content here -->
                    <ul>
					  <li>咖啡</li>
					  <li>牛奶</li>
					  <li>茶</li>
					</ul>
                </div>
            </figure>
            <figure>
                <div class="panther-Carousel-panelTitleBar"><span class="panther-Carousel-panelTitle">&lt; 3 &gt;</span></div>
                <div class="panther-Carousel-panelContent">
                    <!-- Panel 3: Add your content here -->
                    <p>This is a paragraph</p>
					<p>This is another paragraph</p>
                </div>
            </figure>
            <figure>
                <div class="panther-Carousel-panelTitleBar"><span class="panther-Carousel-panelTitle">&lt; 4 &gt;</span></div>
                <div class="panther-Carousel-panelContent">
                    <!-- Panel 4: Add your content here -->
                </div>
            </figure>
              <figure>
                <div class="panther-Carousel-panelTitleBar"><span class="panther-Carousel-panelTitle">&lt; 5 &gt;</span></div>
                <div class="panther-Carousel-panelContent">
                    <!-- Panel 4: Add your content here -->
                </div>
            </figure>
              <figure>
                <div class="panther-Carousel-panelTitleBar"><span class="panther-Carousel-panelTitle">&lt; 6 &gt;</span></div>
                <div class="panther-Carousel-panelContent">
                    <!-- Panel 4: Add your content here -->
                </div>
            </figure>
        </div>
    </section>
</section>

<script>
// Initialize carousel
Panther.Carousel.init({
    id: 'carousel1'
});
</script>

</body>
</html>