Array.prototype.findBy = function(callbfn) {
	for (i in this) {
		o = this[i];
		if (callbfn(o)) {
			return o;
		}
	}
};

function hamatoAutocomplete(textfieldObj, hiddeninpObj, kvArray) {
	// Autocomplete für das Textfeld aktivieren
	textfieldObj.autocomplete({
		source : kvArray,
		autofocus : true,
		focus : function(event, ui) {
			textfieldObj.val(ui.item.value);
			textfieldObj.trigger("change");
			return false;
		},
		select : function(event, ui) {
			textfieldObj.val(ui.item.value);
			textfieldObj.trigger("change");
			hiddeninpObj.val(ui.item.key);
			hiddeninpObj.trigger("change");

			return false;
		},
		change : function(event, ui) {
			item = kvArray.findBy(function(e) {
				return e.key == hiddeninpObj.val();
			});
			if (item == undefined || item.value != textfieldObj.val()) {
				// Wenn die ID im hidden Input != dem Hinterlegten Wert im
				// InputField
				// beides zurücksetzen
				hiddeninpObj.val(-1);
				hiddeninpObj.trigger("change");
				textfieldObj.val("");
				textfieldObj.trigger("change");
			} else {
				hiddeninpObj.trigger("change");
				textfieldObj.trigger("change");
			}
		},
		close : function(event, ui) {
			hiddeninpObj.trigger("change");
			textfieldObj.trigger("change");
		}
	});
	// Prüfen, ob bereits ein wert im Textfeld gesetzt wurde
	if (textfieldObj.val() != undefined && textfieldObj.val() != "") {
		// Überprüfen, ob in Liste vorhanden.
		foundItem = kvArray.findBy(function(e) {
			return e.value == textfieldObj.val();
		});
		if (foundItem != undefined) {
			// beide setzen
			textfieldObj.val(foundItem.value);
			hiddeninpObj.val(foundItem.key);
			hiddeninpObj.trigger("change");
			textfieldObj.trigger("change");
		}
	} else {
		// beide zurücksetzen
		textfieldObj.val("");
		hiddeninpObj.val("-1");
		hiddeninpObj.trigger("change");
		textfieldObj.trigger("change");
	}
}