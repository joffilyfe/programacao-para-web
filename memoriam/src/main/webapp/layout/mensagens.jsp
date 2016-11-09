				<c:if test="${not empty msgs}">
					<div align="left">
						<div style="color: red">
							<ul style="padding-left:0px;">
								<c:forEach var="msg" items="${msgs}">
									<li style="list-style-type: none;">${msg}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>