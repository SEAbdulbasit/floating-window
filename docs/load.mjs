import { instantiate } from './floatingwindow.uninstantiated.mjs';

await wasmSetup;

instantiate({ skia: Module['asm'] });
