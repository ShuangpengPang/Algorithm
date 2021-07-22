package com.shuangpeng;

import java.util.Base64;

public class Temp {
    static String string = "(function (window) {\n" +
            "    console.log('js init');\n" +
            "    if (window._inject_flag) return;\n" +
            "    if (typeof BugInsightJSBridge == 'undefined') {\n" +
            "        return;\n" +
            "    }\n" +
            "    var doc = document;\n" +
            "    if (doc.URL == '') {\n" +
            "        console.log('document.URL is null');\n" +
            "        return;\n" +
            "    }\n" +
            "    var config = {\n" +
            "        upload_url: 'https://api.shujiantec.com:8082/data-upload',\n" +
            "        token: 'bf270b04132b907bea841454e4db56e8',\n" +
            "\n" +
            "        // token: '10ceea001d4fc33565198eda24a7d1f0',\n" +
            "        // upload_url: 'https://local.shujiantec.com:8082/data-upload',\n" +
            "    };\n" +
            "    var util = {\n" +
            "        wrap: function (force, obj, name, wpr, oname) {\n" +
            "            try {\n" +
            "                var raw = obj[name];\n" +
            "            } catch (e) {\n" +
            "                if (!force) return false;\n" +
            "            }\n" +
            "            if (!raw && !force) return false;\n" +
            "            if (raw && raw._ty_wrap) return false;\n" +
            "            try {\n" +
            "                obj[name] = wpr(raw, oname);\n" +
            "            } catch (e) {\n" +
            "                return false;\n" +
            "            }\n" +
            "            ;\n" +
            "            obj[name]._ty_wrap = [raw];\n" +
            "            return true;\n" +
            "        },\n" +
            "        unwrap: function (obj, name) {\n" +
            "            try {\n" +
            "                var raw = obj[name]._ty_wrap;\n" +
            "                if (raw) {\n" +
            "                    obj[name] = raw[0];\n" +
            "                }\n" +
            "            } catch (e) {\n" +
            "            }\n" +
            "        },\n" +
            "        each: function each(ary, func) {\n" +
            "            if (ary) {\n" +
            "                var i;\n" +
            "                for (i = 0; i < ary.length; i += 1) {\n" +
            "                    if (ary[i] && func(ary[i], i, ary)) {\n" +
            "                        break;\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        sh: function (obj, evt, handler, f) {\n" +
            "            if (obj.addEventListener) {\n" +
            "                return obj.addEventListener(evt, handler, f);\n" +
            "            } else if (obj.attachEvent) {\n" +
            "                return obj.attachEvent('on' + evt, handler);\n" +
            "            }\n" +
            "            return false;\n" +
            "        },\n" +
            "        stringify: stringify,\n" +
            "        trim: trim ?\n" +
            "            function (text) {\n" +
            "                return text == null ?\n" +
            "                    \"\" :\n" +
            "                    trim.call(text);\n" +
            "            } : function (text) {\n" +
            "                return text == null ?\n" +
            "                    \"\" :\n" +
            "                    text.toString().replace(/^\\s+/, \"\").replace(/\\s+$/, \"\");\n" +
            "            },\n" +
            "        extend: function (target, source) {\n" +
            "            if (target && source) {\n" +
            "                for (var key in source) {\n" +
            "                    if (source.hasOwnProperty(key)) {\n" +
            "                        target[key] = source[key];\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "            return target;\n" +
            "        },\n" +
            "        bind: function (func, context) {\n" +
            "            return function () {\n" +
            "                return func.apply(context, arguments);\n" +
            "            }\n" +
            "        },\n" +
            "\n" +
            "        uuid: function () {\n" +
            "            var d = Date.now();\n" +
            "            if (typeof performance !== 'undefined' && typeof performance.now === 'function') {\n" +
            "                d += performance.now(); //use high-precision timer if available\n" +
            "            }\n" +
            "            return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {\n" +
            "                var r = (d + Math.random() * 16) % 16 | 0;\n" +
            "                d = Math.floor(d / 16);\n" +
            "                return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);\n" +
            "            });\n" +
            "        },\n" +
            "        uploadData: function (data) {\n" +
            "            // data.distinct_id = ''\n" +
            "            // data.lib = {\n" +
            "            //     $lib: 'js',\n" +
            "            //     $lib_method: 'code',\n" +
            "            //     $lib_version: String(common.para.lib_version)\n" +
            "            // }\n" +
            "\n" +
            "            var dataString = JSON.stringify(data);\n" +
            "            util.ajax({\n" +
            "                url: config.upload_url,\n" +
            "                type: 'POST',\n" +
            "                cors: true,\n" +
            "                // data: encodeURIComponent(util.base64Encode(dataString)),\n" +
            "                data: dataString,\n" +
            "                contentType: 'application/json',\n" +
            "                header: {\n" +
            "                    'ShuJian-Run': false\n" +
            "                },\n" +
            "                success: function (data) {\n" +
            "                    console.log('数据发送成功！', data)\n" +
            "                }\n" +
            "            });\n" +
            "        },\n" +
            "        ajax: function (para) {\n" +
            "            para.timeout = para.timeout || 20000;\n" +
            "\n" +
            "            // para.credentials = (typeof para.credentials) === 'undefined' ? true : para.credentials;\n" +
            "\n" +
            "            function getJSON(data) {\n" +
            "                if (!data) {\n" +
            "                    return '';\n" +
            "                }\n" +
            "                try {\n" +
            "                    return JSON.parse(data);\n" +
            "                } catch (e) {\n" +
            "                    return {};\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            var g = util.xhr(para.cors);\n" +
            "\n" +
            "            if (!g) {\n" +
            "                return false;\n" +
            "            }\n" +
            "\n" +
            "            if (!para.type) {\n" +
            "                para.type = para.data ? 'POST' : 'GET';\n" +
            "            }\n" +
            "\n" +
            "            para.success = function () {\n" +
            "            };\n" +
            "            para.error = function () {\n" +
            "            };\n" +
            "\n" +
            "            try {\n" +
            "                if (typeof g === 'object' && ('timeout' in g)) {\n" +
            "                    g.timeout = para.timeout;\n" +
            "                } else {\n" +
            "                    setTimeout(function () {\n" +
            "                        g.abort();\n" +
            "                    }, para.timeout + 500);\n" +
            "                }\n" +
            "            } catch (e) {\n" +
            "                try {\n" +
            "                    setTimeout(function () {\n" +
            "                        g.abort();\n" +
            "                    }, para.timeout + 500);\n" +
            "                } catch (e2) {\n" +
            "                    console.error('setTimeout error: ', e2)\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            g.onreadystatechange = function () {\n" +
            "                try {\n" +
            "                    if (g.readyState == 4) {\n" +
            "                        if ((g.status >= 200 && g.status < 300) || g.status == 304) {\n" +
            "                            para.success(getJSON(g.responseText));\n" +
            "                        } else {\n" +
            "                            para.error(getJSON(g.responseText), g.status);\n" +
            "                        }\n" +
            "                        g.onreadystatechange = null;\n" +
            "                        g.onload = null;\n" +
            "                    }\n" +
            "                } catch (e) {\n" +
            "                    g.onreadystatechange = null;\n" +
            "                    g.onload = null;\n" +
            "                }\n" +
            "\n" +
            "            };\n" +
            "\n" +
            "            g.open(para.type, para.url, true);\n" +
            "\n" +
            "            try {\n" +
            "                if (para.credentials) {\n" +
            "                    g.withCredentials = true;\n" +
            "                }\n" +
            "                if (para.header) {\n" +
            "                    for (var i in para.header) {\n" +
            "                        g.setRequestHeader(i, para.header[i]);\n" +
            "                    }\n" +
            "                }\n" +
            "\n" +
            "                if (para.data) {\n" +
            "                    if (!para.cors) {\n" +
            "                        g.setRequestHeader(\"X-Requested-With\", \"XMLHttpRequest\");\n" +
            "                    }\n" +
            "                    if (para.contentType === 'application/json') {\n" +
            "                        g.setRequestHeader(\"Content-type\", \"application/json; charset=UTF-8\");\n" +
            "                    } else {\n" +
            "                        g.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
            "                    }\n" +
            "\n" +
            "                }\n" +
            "            } catch (e) {\n" +
            "                console.log('error: ', e)\n" +
            "            }\n" +
            "\n" +
            "            g.send(para.data || null);\n" +
            "        },\n" +
            "        xhr: function (cors) {\n" +
            "            if (cors) {\n" +
            "                if (typeof window.XMLHttpRequest !== 'undefined' && (\"withCredentials\" in new XMLHttpRequest())) {\n" +
            "                    return new XMLHttpRequest();\n" +
            "                } else if (typeof XDomainRequest !== \"undefined\") {\n" +
            "                    return new XDomainRequest();\n" +
            "                } else {\n" +
            "                    return null;\n" +
            "                }\n" +
            "            } else {\n" +
            "                if (typeof window.XMLHttpRequest !== 'undefined') {\n" +
            "                    return new XMLHttpRequest();\n" +
            "                }\n" +
            "                if (window.ActiveXObject) {\n" +
            "                    try {\n" +
            "                        return new ActiveXObject('Msxml2.XMLHTTP')\n" +
            "                    } catch (d) {\n" +
            "                        try {\n" +
            "                            return new ActiveXObject('Microsoft.XMLHTTP')\n" +
            "                        } catch (d) {\n" +
            "                            console.log('xhr error: ', d)\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    };\n" +
            "\n" +
            "    var bugInsight_web = window._inject_flag = {\n" +
            "        st: now(),\n" +
            "        ra: [],\n" +
            "    };\n" +
            "\n" +
            "    console.log('step1');\n" +
            "    var trim = String.prototype.trim;\n" +
            "    if (!String.prototype.startsWith) {\n" +
            "        String.prototype.startsWith = function (searchString, position) {\n" +
            "            position = position || 0;\n" +
            "            return this.indexOf(searchString, position) === position;\n" +
            "        };\n" +
            "    }\n" +
            "\n" +
            "    function stringify(value) {\n" +
            "        switch (typeof value) {\n" +
            "            case 'object':\n" +
            "                if (!value) {\n" +
            "                    return 'null';\n" +
            "                }\n" +
            "                if (value instanceof Array) {\n" +
            "                    var result = '[';\n" +
            "                    for (var i = 0; i < value.length; i++) {\n" +
            "                        result += ((i > 0) ? ',' : '') + stringify(value[i]);\n" +
            "                    }\n" +
            "                    return result + ']';\n" +
            "                }\n" +
            "                if (value instanceof Date) {\n" +
            "                    return value.getTime().toString();\n" +
            "                }\n" +
            "                var result = '{';\n" +
            "                var i = 0;\n" +
            "                for (var key in value) {\n" +
            "                    if (typeof value[key] === 'function') continue;\n" +
            "                    var val = stringify(value[key]);\n" +
            "                    result += ((i > 0) ? ',' : '') + stringify(key) + ':' + val;\n" +
            "                    i++;\n" +
            "                }\n" +
            "                return result + '}';\n" +
            "            case 'string':\n" +
            "                return '\\\"' + value.replace(/([\\\"\\\\])/g, '\\\\$1').replace(/\\n/g, '\\\\n') + '\\\"';\n" +
            "            case 'number':\n" +
            "                return value.toString();\n" +
            "            case 'boolean':\n" +
            "                return value ? 'true' : 'false';\n" +
            "            case 'function':\n" +
            "                return stringify(value.toString());\n" +
            "            case 'undefined':\n" +
            "            default:\n" +
            "                return '\\\"undefined\\\"';\n" +
            "        }\n" +
            "    };\n" +
            "\n" +
            "    function now() {\n" +
            "        return Date.now ? Date.now() : (new Date()).valueOf();\n" +
            "    };\n" +
            "\n" +
            "    var performance = window.performance ? window.performance : window.Performance;\n" +
            "    if (performance) {\n" +
            "        util.sh(performance, 'resourcetimingbufferfull', function () {\n" +
            "            var rcs = performance.getEntriesByType(\"resource\");\n" +
            "            if (!rcs) {\n" +
            "                return;\n" +
            "            }\n" +
            "            bugInsight_web.ra = bugInsight_web.ra.concat(rcs);\n" +
            "            performance.clearResourceTimings();\n" +
            "        }, false);\n" +
            "        util.sh(performance, 'webkitresourcetimingbufferfull', function () {\n" +
            "            var rcs = performance.getEntriesByType(\"resource\");\n" +
            "            if (!rcs) {\n" +
            "                return;\n" +
            "            }\n" +
            "            bugInsight_web.ra = bugInsight_web.ra.concat(rcs);\n" +
            "            performance.webkitClearResourceTimings();\n" +
            "        }, false);\n" +
            "    }\n" +
            "\n" +
            "    var metric = {\n" +
            "        ready: function () {\n" +
            "            return bugInsight_web.load_time;\n" +
            "        },\n" +
            "        send: function () {\n" +
            "            console.log('call send');\n" +
            "            if (this.sended) {\n" +
            "                return false;\n" +
            "            }\n" +
            "            if (!this.ready()) {\n" +
            "                return false;\n" +
            "            }\n" +
            "            var s = bugInsight_web.st;\n" +
            "\n" +
            "            function get_pageurl_metric() {\n" +
            "                console.log('call get_pageurl_metric');\n" +
            "                var r = {};\n" +
            "                if (performance && performance.timing) {\n" +
            "                    var timing = performance.timing;\n" +
            "                    navigationStart = timing.navigationStart;\n" +
            "                    result = {\n" +
            "                        navigationStart: navigationStart,\n" +
            "                        url: doc.URL,\n" +
            "                        fetchStart: timing[\"fetchStart\"] - navigationStart,\n" +
            "                        connectStart: timing[\"connectStart\"] - navigationStart,\n" +
            "                        connectEnd: timing[\"connectEnd\"] - navigationStart,\n" +
            "                        domainLookupStart: timing[\"domainLookupStart\"] - navigationStart,\n" +
            "                        domainLookupEnd: timing[\"domainLookupEnd\"] - navigationStart,\n" +
            "                        requestStart: timing[\"requestStart\"] - navigationStart,\n" +
            "                        responseStart: timing[\"responseStart\"] - navigationStart,\n" +
            "                        responseEnd: timing[\"responseEnd\"] - navigationStart,\n" +
            "                        domContentLoadedEventStart: timing[\"domContentLoadedEventStart\"] - navigationStart,\n" +
            "                        domContentLoadedEventEnd: timing[\"domContentLoadedEventEnd\"] - navigationStart,\n" +
            "                        domInteractive: timing[\"domInteractive\"] - navigationStart,\n" +
            "                        domComplete: timing[\"domComplete\"] - navigationStart,\n" +
            "                        domLoading: timing[\"domLoading\"] - navigationStart,\n" +
            "                        loadEventStart: timing[\"loadEventStart\"] - navigationStart,\n" +
            "                        loadEventEnd: timing[\"loadEventEnd\"] - navigationStart,\n" +
            "                        unloadEventStart: timing[\"unloadEventStart\"] - navigationStart,\n" +
            "                        unloadEventEnd: timing[\"unloadEventEnd\"] - navigationStart\n" +
            "                    };\n" +
            "\n" +
            "                    if (timing.secureConnectionStart) {\n" +
            "                        result.secureConnectionStart = timing[\"secureConnectionStart\"] - navigationStart;\n" +
            "                    }\n" +
            "\n" +
            "                    console.log(result);\n" +
            "                    return result;\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            function get_navigation() {\n" +
            "                console.log('get navigation info');\n" +
            "                var pf = performance;\n" +
            "            }\n" +
            "\n" +
            "            function get_rc_array() {\n" +
            "                var pf = performance;\n" +
            "                console.log('call get_rc_array');\n" +
            "                if (pf && pf.getEntriesByType) {\n" +
            "                    var r = {};\n" +
            "                    var rc = bugInsight_web.ra;\n" +
            "                    var rcs = pf.getEntriesByType(\"resource\");\n" +
            "                    if (rcs) {\n" +
            "                        rc = rc.concat(rcs);\n" +
            "                        if (pf.clearResourceTimings) {\n" +
            "                            pf.clearResourceTimings();\n" +
            "                        } else if (pf.webkitClearResourceTimings) {\n" +
            "                            pf.webkitClearResourceTimings();\n" +
            "                        }\n" +
            "                    }\n" +
            "                    r.res = [];\n" +
            "                    for (var i = 0; i < rc.length; i++) {\n" +
            "                        var t = rc[i];\n" +
            "\n" +
            "                        function v(n) {\n" +
            "                            return (t[n] > 0) ? t[n] : 0;\n" +
            "                        }\n" +
            "\n" +
            "                        var item = {\n" +
            "                            startTime: v(\"startTime\"),\n" +
            "                            initiatorType: t.initiatorType,\n" +
            "                            name: t.name,\n" +
            "                            duration: v(\"duration\"),\n" +
            "                            fetchStart: v(\"fetchStart\"),\n" +
            "                            domainLookupStart: v(\"domainLookupStart\"),\n" +
            "                            domainLookupEnd: v(\"domainLookupEnd\"),\n" +
            "                            connectStart: v(\"connectStart\"),\n" +
            "                            connectEnd: v(\"connectEnd\"),\n" +
            "                            secureConnectionStart: v(\"secureConnectionStart\"),\n" +
            "                            requestStart: v(\"requestStart\"),\n" +
            "                            responseStart: v(\"responseStart\"),\n" +
            "                            responseEnd: v(\"responseEnd\"),\n" +
            "                            transferSize: t.transferSize || 0,\n" +
            "                            encodedBodySize: t.encodedBodySize || 0,\n" +
            "                            decodedBodySize: t.decodedBodySize || 0\n" +
            "                        };\n" +
            "                        console.log('push item:' + item);\n" +
            "                        r.res.push(item);\n" +
            "                    }\n" +
            "                    return r;\n" +
            "                }\n" +
            "                return null;\n" +
            "            }\n" +
            "\n" +
            "            var result = {};\n" +
            "            var pf = get_pageurl_metric();\n" +
            "            if (pf.domComplete <= 0 || pf.loadEventEnd <= 0) {\n" +
            "                console.log('invalid performance data');\n" +
            "                return;\n" +
            "            }\n" +
            "            console.log('page url info:' + util.stringify(pf));\n" +
            "\n" +
            "            result = get_rc_array();\n" +
            "            result = result ? util.stringify(result) : '';\n" +
            "\n" +
            "            var dataCombine = {};\n" +
            "            dataCombine.pageres = result;\n" +
            "\n" +
            "            if ((pf.url.startsWith(\"http\") || pf.url.startsWith(\"file:\"))) {\n" +
            "                pf.header = \"\";\n" +
            "                dataCombine.pageurl = pf ? util.stringify(pf) : '';\n" +
            "                console.log('send data to java');\n" +
            "                // BugInsightJSBridge.logJsResult(dataCombine ? util.stringify(dataCombine) : '');\n" +
            "\n" +
            "                var uuid = util.uuid();\n" +
            "                if (pf) {\n" +
            "                    pf.eventName = 'ae_webview';\n" +
            "                    pf.token = config.token;\n" +
            "                    pf.timestamp = Date.now();\n" +
            "                    pf.id = uuid;\n" +
            "                    pf.isUrl = 1;\n" +
            "                    pf.dnsTime = pf.domainLookupEnd - pf.domainLookupStart;\n" +
            "                    pf.tcpTime = pf.connectEnd - pf.connectStart;\n" +
            "                    pf.downloadTime = pf.responseEnd - pf.responseStart;\n" +
            "                    pf.domTreeParseTime = pf.domComplete - pf.domInteractive;\n" +
            "                    pf.whiteScreenTime = pf.domInteractive;\n" +
            "                    pf.domOnLoadTime = pf.loadEventEnd;\n" +
            "                    pf.ttfb = pf.responseStart - pf.requestStart;\n" +
            "                    util.uploadData(pf);\n" +
            "                }\n" +
            "\n" +
            "                if (result && result.length > 0) {\n" +
            "                    result = JSON.parse(result);\n" +
            "                    var items = [];\n" +
            "                    for (var i = 0; i < result.res.length; i++) {\n" +
            "                        var item = result.res[i];\n" +
            "                        item.id = uuid;\n" +
            "                        item.isRes = 1;\n" +
            "                        item.url = item.name;\n" +
            "                        item.dnsTime = item.domainLookupEnd - item.domainLookupStart;\n" +
            "                        item.tcpTime = item.connectEnd - item.connectStart;\n" +
            "                        item.downloadTime = item.responseEnd - item.requestStart;\n" +
            "                        item.ttfb = item.responseStart - item.requestStart;\n" +
            "                        items.push(item);\n" +
            "                    }\n" +
            "                    util.uploadData({eventName: 'ae_webview', token: config.token, timestamp: Date.now(), res: items});\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            // var postError = util.bind(sendError, this);\n" +
            "            // postError();\n" +
            "            // //设置每隔10s发送一次error\n" +
            "            // setInterval(postError, 10000);\n" +
            "\n" +
            "            this.sended = true;\n" +
            "            return true;\n" +
            "        },\n" +
            "        errors: []\n" +
            "    };\n" +
            "\n" +
            "    function onDocLoaded() {\n" +
            "    };\n" +
            "\n" +
            "    function onReadyStateChange() {\n" +
            "        if (doc.readyState === 'complete') {\n" +
            "        }\n" +
            "    };\n" +
            "\n" +
            "    function onWindowLoad(a) {\n" +
            "        console.log('onWindowLoad, status:%d', a);\n" +
            "        if (bugInsight_web.load_time) {\n" +
            "//            return true;\n" +
            "        }\n" +
            "        bugInsight_web.load_time = now();\n" +
            "\n" +
            "        function cb() {\n" +
            "            metric.send();\n" +
            "        };\n" +
            "        if (a === 9) {\n" +
            "            cb();\n" +
            "        } else {\n" +
            "            setTimeout(cb, 0);\n" +
            "        }\n" +
            "    };\n" +
            "\n" +
            "    var ul = null;\n" +
            "    var isFromUnLoad = false;\n" +
            "\n" +
            "    function onWindowUnload() {\n" +
            "        if (!ul) {\n" +
            "            isFromUnLoad = true;\n" +
            "            onWindowLoad(9);\n" +
            "        }\n" +
            "        ul = 1;\n" +
            "    };\n" +
            "\n" +
            "    var w_evts = [\n" +
            "        ['load', onWindowLoad],\n" +
            "        ['beforeunload', onWindowUnload],\n" +
            "        ['pagehide', onWindowUnload],\n" +
            "        ['unload', onWindowUnload]\n" +
            "    ];\n" +
            "\n" +
            "    for (var i = 0; i < w_evts.length; i++) {\n" +
            "        util.sh(window, w_evts[i][0], w_evts[i][1], false)\n" +
            "    }\n" +
            "\n" +
            "    var d_evts = [\n" +
            "        ['DOMContentLoaded', onDocLoaded],\n" +
            "        ['readystatechange', onReadyStateChange]\n" +
            "    ];\n" +
            "    for (var i = 0; i < d_evts.length; i++) {\n" +
            "        util.sh(doc, d_evts[i][0], d_evts[i][1], false)\n" +
            "    }\n" +
            "\n" +
            "    function onErrorEvent(obj) {\n" +
            "        console.log(\"onErrorEvent happened\")\n" +
            "        var a = arguments,\n" +
            "            ud = \"unknown\",\n" +
            "            p = [now()];\n" +
            "        if (a.length == 0) {\n" +
            "            return;\n" +
            "        }\n" +
            "        if (typeof obj === 'string') {\n" +
            "        } else if (obj instanceof Event || (window.ErrorEvent && obj instanceof ErrorEvent)) {\n" +
            "            p[1] = obj.message || ((obj.error && obj.error[\"constructor\"].name) + (obj.error && obj.error.message)) || \"\";\n" +
            "            p[2] = obj.lineno ? obj.lineno : 0;\n" +
            "            p[3] = obj.colno ? obj.colno : 0;\n" +
            "            p[4] = obj.filename || (obj.error && obj.error.fileName) || (obj.target && obj.target.baseURI) || \"\";\n" +
            "            if (!p[4]) {\n" +
            "                console.log('on error: filename is empty and ignored');\n" +
            "                return;\n" +
            "            }\n" +
            "            if (p[4] == doc.URL) {\n" +
            "                p[4] = '#';\n" +
            "            }\n" +
            "            if (obj.error) {\n" +
            "                p[5] = obj.error.stack;\n" +
            "                p[6] = obj.error.moduleName;\n" +
            "            } else {\n" +
            "                p[5] = null;\n" +
            "                p[6] = null;\n" +
            "            }\n" +
            "            // var errorKey = errorId(p[1], p[2], p[3], p[6]);\n" +
            "            //p[7] = errorTypeCache[errorKey] ? 0 : 1;//这里是表示是否是重复的url吧~~\n" +
            "            //改成手机端需要的\n" +
            "            p[7] = 0;\n" +
            "            // if(!isFirstError){\n" +
            "            //     p[7] = 1; //该js错误是否是当前页面的第一个js错误\n" +
            "            //     isFirstError = true;\n" +
            "            // }\n" +
            "            // errorTypeCache[errorKey] = true;\n" +
            "            if (p[1] === ud && p[4] === ud) {\n" +
            "                return;\n" +
            "            }\n" +
            "            fixInfo(p);\n" +
            "\n" +
            "            if (obj.error) {\n" +
            "                p[8] = obj.error.name;\n" +
            "            } else {\n" +
            "                p[8] = null;\n" +
            "            }\n" +
            "            p[9] = now() - getStartTime(); //错误发生时间\n" +
            "        }\n" +
            "        metric.errors.push(p);\n" +
            "    }\n" +
            "\n" +
            "    function fixInfo(p) {\n" +
            "        // only fix stack info when there is a module name.\n" +
            "        if (p[6]) {\n" +
            "            var filename = p[4];\n" +
            "            var stack = p[5];\n" +
            "            if (stack && typeof stack === 'string' && filename) {\n" +
            "                stack = stack.split(/\\n/);\n" +
            "                var result = URL_REG.exec(stack[0]);\n" +
            "                if (!result) {\n" +
            "                    result = URL_REG.exec(stack[1]);\n" +
            "                }\n" +
            "                if (result && result[1] != filename) {\n" +
            "                    p[4] = result[1] || filename;\n" +
            "                    p[2] = result[2] || p[2];\n" +
            "                    p[3] = result[3] || p[3];\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    function errorId(msg, lineno, colno, module) {\n" +
            "        return String(msg) + String(lineno) + String(colno) + String(module);\n" +
            "    }\n" +
            "\n" +
            "    function getStartTime() {\n" +
            "        return performance.timing && performance.timing.navigationStart || bugInsight_web.st;\n" +
            "    }\n" +
            "\n" +
            "    function sendError() {\n" +
            "        console.log(\"metric errors length:\" + metric.errors.length);\n" +
            "        if (metric.errors.length) {\n" +
            "            var errors = metric.errors;\n" +
            "            // var that = this;\n" +
            "            //考虑到error是分为不同的部分传输的，分为dom load之前和之后，此是在之后传输的，\n" +
            "            //所以分成了2个函数了，这个sendError是在之后进行传输\n" +
            "            try {\n" +
            "                console.log('after dom error:' + (errors ? util.stringify(errors) : ''));\n" +
            "                util.each(errors, function (err) {\n" +
            "                    // nbsJsBridge.logJsError(guid, doc.URL, err[8], err[0],\n" +
            "                    //     err[1], err[2], err[3], err[5], err[4], err[7], \"\", getStartTime(), err[9]);\n" +
            "                    // BugInsightJSBridge.logJsError(\"guid\", doc.URL, err[8], err[0],\n" +
            "                    //     err[1], err[2], err[3], err[5], err[4], err[7], \"\", getStartTime(), err[9]);\n" +
            "\n" +
            "                    var jsErrorData = {\n" +
            "                        // pvid: \"guid\",\n" +
            "                        eventName: 'ae_jserror',\n" +
            "                        token: config.token,\n" +
            "                        timestamp: Date.now(),\n" +
            "                        url: doc.URL,\n" +
            "                        typeName: err[8],\n" +
            "                        // msg: err[0],\n" +
            "                        line: 0,\n" +
            "                        column: err[2],\n" +
            "                        fileName: err[3],\n" +
            "                        stack: err[5],\n" +
            "                        jsErrorCount: 0,\n" +
            "                        // isFirstJsErr: err[7],\n" +
            "                        // cdnVendor: \"\",\n" +
            "                        pageStartTimeInSec: getStartTime()\n" +
            "                        // offsetTimestamp: err[9]\n" +
            "                    };\n" +
            "                    util.uploadData(jsErrorData);\n" +
            "                });\n" +
            "            } catch (e) {\n" +
            "            }\n" +
            "            // errors.errors = [];\n" +
            "            metric.errors = [];\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    if (window.addEventListener) {\n" +
            "        util.sh(window, 'error', onErrorEvent, false);\n" +
            "    }\n" +
            "\n" +
            "    if (!this.setJsErrorSend) {\n" +
            "        //js的独立出来发送吧，有时候send函数没有被调用\n" +
            "        var postError = util.bind(sendError, this);\n" +
            "        postError();\n" +
            "        //设置每隔10s发送一次error\n" +
            "        setInterval(postError, 10000);\n" +
            "        this.setJsErrorSend = true;\n" +
            "    }\n" +
            "})(window)\n";


    public static void main(String[] args) {
        String encodeinfos = Base64Utils.encodeString(string);
        int i = 1;
    }
}
