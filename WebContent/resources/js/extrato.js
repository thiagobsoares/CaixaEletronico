/**
 * 
 */

$(document)
		.ready(
				function() {
					$('#table-extrato').DataTable();
					$('.radio-data-extrato  input').click(function() {
						$('.radio-data-escolhida').remove();
					});

					$('.periodo-especifico')
							.click(
									function() {
										$('.campo-data-escolhida')
												.append(
														"<label class='radio-data-escolhida' >Data Inicio: </label><input class='radio-data-escolhida' type='date' name='dataInicio'/>")
										$('.campo-data-escolhida')
												.append(
														"<label class='radio-data-escolhida' >Data Fim: </label><input class='radio-data-escolhida' type='date' name='dataFim'/>")
										$('.radio-data-escolhida').css({
											"margin" : "15px"
										});
										$('.campo-data-escolhida').css({
											"text-align" : "right"
										});
									});
				});
