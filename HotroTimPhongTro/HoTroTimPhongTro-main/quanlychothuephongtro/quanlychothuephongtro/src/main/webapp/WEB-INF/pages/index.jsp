<%-- 
    Document   : index
    Created on : Jul 15, 2023, 10:48:39 AM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<style>

    .mySlides {
        display: none
    }
    img {
        vertical-align: middle;
    }

    /* Slideshow container */
    .slideshow-container {
        max-width: 1000px;
        position: relative;
        margin: auto;
    }

    /* Next & previous buttons */
    .prev, .next {
        cursor: pointer;
        position: absolute;
        top: 50%;
        width: auto;
        padding: 16px;
        margin-top: -22px;
        color: white;
        font-weight: bold;
        font-size: 18px;
        transition: 0.6s ease;
        border-radius: 0 3px 3px 0;
        user-select: none;
    }

    /* Position the "next button" to the right */
    .next {
        right: 0;
        border-radius: 3px 0 0 3px;
    }

    /* On hover, add a black background color with a little bit see-through */
    .prev:hover, .next:hover {
        background-color: rgba(0,0,0,0.8);
    }

    /* Caption text */
    .text {
        color: #f2f2f2;
        font-size: 15px;
        padding: 8px 12px;
        position: absolute;
        bottom: 8px;
        width: 100%;
        text-align: center;
    }

    /* Number text (1/3 etc) */
    .numbertext {
        color: #f2f2f2;
        font-size: 12px;
        padding: 8px 12px;
        position: absolute;
        top: 0;
    }

    /* The dots/bullets/indicators */
    .dot {
        cursor: pointer;
        height: 15px;
        width: 15px;
        margin: 0 2px;
        background-color: #bbb;
        border-radius: 50%;
        display: inline-block;
        transition: background-color 0.6s ease;
    }

    .activeMySlide, .dot:hover {
        background-color: #717171;
    }

    /* Fading animation */
    .fadeSlide {
        animation-name: fade;
        animation-duration: 1.5s;
    }

    @keyframes fade{
        from {
            opacity: .4
        }
        to{
            opacity: 1
        }

    }

    /* On smaller screens, decrease text size */
    @media only screen and (max-width: 300px) {
        .prev, .next,.text {
            font-size: 11px
        }
    }

</style>
<div class="section">
    <div class="container">
        <div class="row justify-content">
            <div class="col-lg-7">
                <div class="img-property-slide-wrap">
                    <div class="tns-outer" id="tns1-ow">
                        <div class="slideshow-container">

                            <div class="mySlides fadeSlide">
                                <div class="numbertext">1 / 4</div>
                                <img src="https://res.cloudinary.com/dicxcmntw/image/upload/v1694503468/o1wivj8pxfqymdfwwguz.jpg" style="width:100%">
                                <div class="text">Caption Text</div>
                            </div>

                            <div class="mySlides fadeSlide">
                                <div class="numbertext">2 / 4</div>
                                <img src="https://res.cloudinary.com/dicxcmntw/image/upload/v1694503468/o1wivj8pxfqymdfwwguz.jpg" style="width:100%">
                                <div class="text">Caption Two</div>
                            </div>

                            <div class="mySlides fadeSlide">
                                <div class="numbertext">3 / 4</div>
                                <img src="https://res.cloudinary.com/dicxcmntw/image/upload/v1694503468/o1wivj8pxfqymdfwwguz.jpg" style="width:100%">
                                <div class="text">Caption Three</div>
                            </div>
                            <div class="mySlides fadeSlide">
                                <div class="numbertext">4 / 4</div>
                                <img src="https://res.cloudinary.com/dicxcmntw/image/upload/v1694503468/o1wivj8pxfqymdfwwguz.jpg" style="width:100%">
                                <div class="text">Caption 4</div>
                            </div>    
                            <a class="prev" onclick="plusSlides(-1)">❮</a>
                            <a class="next" onclick="plusSlides(1)">❯</a>

                        </div>
                        <br>

                        <div style="text-align:center">
                            <span class="dot" onclick="currentSlide(1)"></span> 
                            <span class="dot" onclick="currentSlide(2)"></span> 
                            <span class="dot" onclick="currentSlide(3)"></span> 
                            <span class="dot" onclick="currentSlide(4)"></span> 
                        </div>
                    </div>
                    <iframe class="mt-5" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s" width="100%" height="256" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </div>
            <div class="col-lg-4">
                <h5 class="heading text-primary">5232 California Ave. 21BC</h5>
                <p class="meta">California, United States</p>
                <p class="text-black-50">
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ratione
                    laborum quo quos omnis sed magnam id, ducimus saepe, debitis error
                    earum, iste dicta odio est sint dolorem magni animi tenetur.
                </p>
                <p class="text-black-50">
                    Perferendis eligendi reprehenderit, assumenda molestias nisi eius
                    iste reiciendis porro tenetur in, repudiandae amet libero.
                    Doloremque, reprehenderit cupiditate error laudantium qui, esse
                    quam debitis, eum cumque perferendis, illum harum expedita.
                </p>

                <div class="d-block agent-box mt-5">
                    <div class="img mb-4 d-flex">
                        <img src="https://res.cloudinary.com/dicxcmntw/image/upload/v1694503468/o1wivj8pxfqymdfwwguz.jpg" style="border-radius: 50%;" class="img-circle" alt="Cinque Terre" width="50" height="50"> 
                        <h3 class="ml-2">Alicia Huston</h3>
                    </div>
                    <div class="">
                        <div class="meta mb-3">Real Estate</div>
                        <p>
                            Lorem ipsum dolor sit amet consectetur adipisicing elit.
                            Ratione laborum quo quos omnis sed magnam id ducimus saepe
                        </p>

                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
<script>
    let slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("dot");
        if (n > slides.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = slides.length
        }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }

        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" activeMySlide", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " activeMySlide";
    }
</script>