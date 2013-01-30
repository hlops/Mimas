/**
 * Load plugin
 * @param context - plugin context (dom element container)
 * @param name - one or few plugin names
 * @param params - parameters: {
 *      changer: default
 * }
 */

window.PlugInner = function () {
    var PlugInner = this;
    var $context, params;

    function name2url(name) {
        return "pages/" + name + '.html';
    }

    PlugInner.load = function(name, alias, data) {
        var plugins = $context.data("plugins");
        if (!plugins) {
            $context.data("plugins", plugins = {});
        }
        var fullName = alias ? name + "_" + alias : name;
        if (!plugins[fullName]) {
            var $div = params.changer.wrap(fullName);
            $context.append($div);
            plugins[fullName] = $div;
            $div.data("name", name);
            $div.data("alias", alias);
            $div.data("data", data);
            $div.data("fullName", fullName);
            var dataToPost = {v:1};
            $div.load(name2url(name), function(responseText, textStatus, XMLHttpRequest) {
                var $this = $(this);
                if (XMLHttpRequest.status != 200) {
                    $this.append("Sorry, page " + name + " is under construction");
                }
                params.changer.loaded($this, true, data);
            });
        } else {
            params.changer.loaded(plugins[fullName], false, data);
        }
    };

    function getPlugin(alias) {
        return $context.data("plugins")[alias];
    }

    PlugInner.show = function(alias) {
        var $div = getPlugin(alias);
        params.changer.show($div);
    };

    PlugInner.hide = function(alias) {
        var $div = getPlugin(alias);
        params.changer.hide($div);
    };

    function PlugInnerChanger() {
        this.wrap = function (name) {
            var $div = $("<div id='plugin_" + name2id(name) + "'></div>");
            $div.hide();
            return $div;
        };

        this.show = function ($div, data) {
            this.onShow($div, data);
            $div.trigger("plugin.show");
        };

        this.hide = function ($div) {
            this.onHide($div);
            $div.trigger("plugin.hide");
        };

        this.loaded = function ($div, isNew, data) {
            this.onLoad($div, isNew, data);
            $div.trigger("plugin.loaded", isNew);
        };

        function name2id(name) {
            return name.replace(/[\/#\.]/g, "_");
        }

        this.onShow = function($div) {
        };

        this.onHide = function($div) {
        };

        this.onLoad = function ($div, isNew) {
        };
    }

    function PlugInnerMenuChanger(parentChanger) {
        var parent_show = parentChanger.show;
        var $prevShownDiv;
        this.show = function ($div) {
            if ($prevShownDiv) {
                if ($prevShownDiv.attr("id") == $div.attr("id")) return;
                this.hide($prevShownDiv);
            }
            $prevShownDiv = $div;
            parent_show.call(this, $div);
        };
    }

    PlugInner.getChanger = function (param) {
        var type;
        if (typeof params == 'string') {
            type = param;
        } else {
            type = param.type;
        }
        var changer = new PlugInnerChanger();
        if (type == 'menu') {
            changer = $.extend(changer, new PlugInnerMenuChanger(changer));
        }
        if (typeof params == 'object') {
            changer = $.extend(changer, param);
        }
        return changer;
    };

    var names = [];
    for (var i = 0; i < arguments.length; i++) {
        var p = arguments[i];
        if (typeof(p) == "string") {
            names.push(p);
        } else if (typeof(p) == "object") {
            var $p = $(p);
            if ($p.parent().length) {
                $context = $p;
            } else {
                params = p;
            }
        }
    }
    if (!$context) {
        alert("Error: Context was not set");
    }
    if (!names.length) {
        //alert("Error: Name was not set");
    }
    params = $.extend({
        changer: {}
    }, params);

    params.changer = PlugInner.getChanger(params.changer);

    for (i = 0; i < names.length; i++) {
        this.load(names[i]);
    }

};


