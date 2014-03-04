<!DOCTYPE HTML>
<html>
    <head>
        <title>Welcome to Belle et Glamour</title>
        <meta http-equiv="Content-Type" content="<?php bloginfo('html_type'); ?>; charset=<?php bloginfo('charset'); ?>" />	
        <meta name="generator" content="WordPress <?php bloginfo('version'); ?>" /> <!-- leave this for stats please -->

        <link rel="stylesheet" href="<?php bloginfo('stylesheet_url'); ?>" type="text/css" media="screen" />
        <link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="<?php bloginfo('rss2_url'); ?>" />
        <link rel="alternate" type="text/xml" title="RSS .92" href="<?php bloginfo('rss_url'); ?>" />
        <link rel="alternate" type="application/atom+xml" title="Atom 0.3" href="<?php bloginfo('atom_url'); ?>" />
        <link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />

        <?php wp_get_archives('type=monthly&format=link'); ?>
        <?php wp_head(); ?>

        <?php if (is_page('contacts')) { ?>
            <script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="<?php bloginfo('template_directory'); ?>/js/verif.js"></script>
        <?php } ?>

        <script src="jquery_fade.js"></script>

        <style type="text/css">
            <!--
            img { border: none; }
            -->
        </style>

    </head>

    <body>
        <div class="container">

            <div class="header">
                <div class="logo">
                    <a href="#"><img src="<?php echo get_bloginfo('template_directory'); ?>/images/Logo10.png" alt="Insert Logo Here" name="Insert_logo" width="960" height="120" id="Insert_logo" style="display:block;" /></a>       
                </div>

                <div id="access" role="navigation">
                    <?php wp_nav_menu(array('container_class' => 'menu-header', 'theme_location' => 'primary', 'include' => '5, 7, 11,377,949')); ?>
                </div>
                
                <!-- end .header --></div>

