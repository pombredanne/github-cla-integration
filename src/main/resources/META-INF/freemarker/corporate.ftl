<html>
<head>

<title>Sign the Corporate GoPivotal, Inc. ${repository.agreement.name} Corporate Contributor License Agreement</title>
<link rel="stylesheet" type="text/css" href="/resources/styles/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/resources/styles/style.css">

</head>
<body>

<#include "header.ftl">

<div class ="container">

	<h1>GoPivotal, Inc. Corporate Contributor License Agreement</h1>

	<em>IMPORTANT-READ CAREFULLY: BY CLICKING THE SUBMIT BUTTON, YOU AGREE TO BE BOUND BY THE TERMS OF THIS CORPORATE CONTRIBUTOR LICENSE AGREEMENT.</em>

	<p>In order to clarify the intellectual property license granted with Contributions (as defined below) from any person or entity, GoPivotal, Inc. ("Pivotal") must have a Contributor License Agreement ("CLA" or "Agreement") on file that has been signed by each Contributor (as defined below), indicating agreement to the license terms below. This license is for your protection as a Contributor as well as for the protection of Pivotal and its users; it does not change your rights to use your own Contributions for any other purpose.</p>

	<p>Please read this document carefully before signing and keep a copy for your records.</p>

	<div class="agreement-content">
		<div class="markdown">${version.corporateAgreementContent}</div>
	</div>

	<form class="sign" method="POST" action="/${repository.name}/corporate">
		<h2>Sign Contributor License Agreement</h2>

		<fieldset>
			<label for="company">Company Name:</label>
			<input type="text" class="text" name="company" id ="company" value="${user.company!}">

			<label for="name">Contact Name:</label>
			<input type="text" class="text" name="name" id ="name" value="${user.name!}">

			<label for="title">Contact Title:</label>
			<input type="text" class="text" name="title" id ="title">

			<label for="email">Contact Email Address:</label>
			<select name="email" required="true">
			<#list emails as email>
				<option value="${email.address}" <#if email.isPrimary()>selected="true"</#if> >${email.address}</option>
			</#list>
			</select>

			<label for="mailingAddress">Contact Mailing Address:</label>
			<textarea name="mailingAddress" id="mailingAddress" class="text"></textarea>

			<label for="country">Contact Country:</label>
			<input type="text" class="text" name="country" id ="country">

			<label for="telephoneNumber">Contact Telephone Number:</label>
			<input type="text" class="text" name="telephoneNumber" id ="telephoneNumber">

			<label>Contribution Email Domains:</label>
			<fieldset class="contribution">
				<ul>
				<#list domains as domain>
					<li>
						<input type="checkbox" name="contribution" value="${domain}">
						<label>${domain}</label>
					</li>
				</#list>
				</ul>
			</fieldset>

			<input type="checkbox" name="agree" id ="agree">
			<label for="checkbox">I have head and agree to the terms of this Contributor License Agreement</label>

			<input type="hidden" name="versionId" value="${version.id}">
			<input type="hidden" name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}"/>
			<button type="submit">Sign</button>
		</fieldset>
	</form>
</div>

</body>
</html>
