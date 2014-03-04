<?php get_header() ?>

<div class="featuredadds">

    <div id="ImageSlider">
        <?php
        if (function_exists("easing_slider")) {
            easing_slider();
        };
        ?>
    </div>
</div>

<div id="content">
    <div id="sidebar">
        <h2 style="margin-left:-15px; margin-top:5px;">CATEGORIES</h2>
        <?php
        echo do_shortcode('[dcwp-jquery-accordion menu="Categories" event="click" save="true" disable="true" expand="false"]');
        ?>
    </div>

    <div id="home_main_content">
        <h2 style="margin-left:-15px; margin-top:5px;">FEATURED ITEMS</h2>
        <?php
        $thumbnails = get_posts('numberposts=9');

        foreach ($thumbnails as $thumbnail) {
            if (has_post_thumbnail($thumbnail->ID)) {
                echo '<div id="thumbnails">';
                echo '<a href="'.get_permalink($thumbnail->ID).'" title="'.esc_attr($thumbnail->post_title).'">';
                echo get_the_post_thumbnail($thumbnail->ID);
                echo '<h5 style="position:absolute; right:0; bottom:0; margin-right:2px; margin-bottom:2px; color:gold;">CHECK IT OUT!</h5>';
                echo '</a>';
                echo '</div>';
            }
        }
        ?>
    </div>

    <div style="clear: both;">
    </div>   

</div>
<?php get_footer() ?>