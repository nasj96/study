### [HMR] Waiting for update signal from WDS...

- 파일 /node_modules/webpack/hot/log.js 수정

```vue.js
module.exports = function(level, msg) {
	// if (shouldLog(level)) {
	// 	if (level === "info") {
	// 		console.log(msg);
	// 	} else if (level === "warning") {
	// 		console.warn(msg);
	// 	} else if (level === "error") {
	// 		console.error(msg);
	// 	}
	// }
};
```