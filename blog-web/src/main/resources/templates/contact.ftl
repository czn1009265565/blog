<!DOCTYPE html>
<html>
	<head>
		<title>ZeNan's Blog</title>
		<#include "components/meta.ftl">
	</head>

	<body id="page">
	<#include "components/header.ftl">

		<div class="content-body">
			<div class="container">
				<div class="row">
					<main class="col-md-12">
						<h1 class="page-title">留言</h1>
						<article class="post">
							<div class="entry-content clearfix">
								<form action="/contact" method="post" class="contact-form">
									<div class="row">
										<div class="col-md-6 col-md-offset-3">
											<input type="text" name="name" placeholder="Name" required>
											<input type="email" name="email" placeholder="Email" required>
											<input type="text" name="subject" placeholder="Subject" required>
											<textarea name="message" rows="7" placeholder="Your Message" required></textarea>
											<button class="btn-send btn-5 btn-5b ion-ios-paperplane"><span>给我留言</span></button>
										</div>
									</div>	<!-- row -->
								</form>
							</div>
						</article>
					</main>
				</div>
			</div>
		</div>
		<#include "components/footer.ftl">

		<#include "components/mobile.ftl">
		<script src="/static/js/script.js"></script>
		
	</body>
</html>
