/* 
 Web Development CA 4
 Created on : 25 Apr 2022, 09:57:15
 Author     : Gursimar, Patrick, Matthew
 Group      : SD1c
 */

let canvas = null
let ctx = null
let mouse = false
let mouseX = 0
let mouseY = 0
let brushIsSet = null
let brush = null
let brushColor = null
let eraser = null
let color = null
let recentColor = null
let size = null
let recentSize = null
let text = null
let textColor = null
let imageWidth = 100
let imageHeight = 100
let imageX = 250
let imageY = 250
let offsetX = 0
let offsetY = 0
let imageLoader = null;
let offscreenCanvas = null
let offscreenCanvasCtx = null
let scribbleCanvas = null
let scribbleCanvasCtx = null
let imageData = null;
let eyeDropButton = null;
let data = null;
let eraserIsSet = null;
let eyedropIsSet = null;
let noImgUploaded = true;
let doubleBufferCtx = null;
let convolvedPixel = null;
let convolutionMatrix = null;
let convolution = false;
let width = 0;
let height = 0;
let alphaOff = null;

// set up the convolution matrix
let embossConvolutionMatrix = [0, 0, 0,
    0, 2, -1,
    0, -1, 0];

let blurConvolutionMatrix = [1, 2, 1,
    2, 4, 2,
    1, 2, 1];

let sharpenConvolutionMatrix = [0, -2, 0,
    -2, 11, -2,
    0, -2, 0];

let edgeDetectionConvolutionMatrix = [1, 1, 1,
    1, -7, 1,
    1, 1, 1];

let noConvolutionMatrix = [0, 0, 0,
    0, 1, 0,
    0, 0, 0];

let spider = new Image()
spider.src = "images/spider.jpg"
let hulk = new Image()
hulk.src = "images/hulk.jpg"
let superman = new Image()
superman.src = "images/super.jpg"

let alphaImage = new Image();
alphaImage.src = 'images/airplane.png';

let newRotationDegrees = null

let images = [
    {src: spider, x: 50, y: 150, width: 200, height: 200, rotation: 0},
    {src: hulk, x: 300, y: 100, width: 200, height: 200, rotation: 0},
    {src: superman, x: 300, y: 300, width: 200, height: 200, rotation: 0}]

let currentImageIndex = 0

window.onload = onAllAssetsLoaded
document.write("<div id='loadingMessage'>Loading...</div>")
function onAllAssetsLoaded() {
    // hide the webpage loading message
    document.getElementById('loadingMessage').style.visibility = "hidden"

    canvas = document.getElementById("myCanvas");
    ctx = canvas.getContext("2d");
    canvas.width = canvas.clientWidth
    canvas.height = canvas.clientHeight

    width = canvas.clientWidth
    height = canvas.clientHeight

    offscreenCanvas = document.createElement('canvas');
    offscreenCanvasCtx = offscreenCanvas.getContext('2d');
    offscreenCanvas.width = canvas.clientWidth
    offscreenCanvas.height = canvas.clientHeight

    scribbleCanvas = document.createElement('canvas');
    scribbleCanvasCtx = scribbleCanvas.getContext('2d');
    scribbleCanvas.width = canvas.clientWidth
    scribbleCanvas.height = canvas.clientHeight

    scribbleCanvasCtx.lineJoin = "round"
    scribbleCanvasCtx.lineCap = "round"

    brush = document.getElementById("brush")
    eraser = document.getElementById("eraser")
    color = document.getElementById("recentColor")
    recentColor = color.value;
    scribbleCanvasCtx.strokeStyle = recentColor

    size = document.getElementById("recentSize")
    recentSize = size.value
    scribbleCanvasCtx.lineWidth = recentSize
    reset = document.getElementById("reset")
    saveLink = document.getElementById("saveLink")
    text = document.getElementById("recentText")
    recentText = text.value

    doubleBuffer = document.createElement('canvas');
    doubleBufferCtx = doubleBuffer.getContext('2d');

    doubleBuffer.width = canvas.clientWidth;
    doubleBuffer.height = canvas.clientHeight;

    convolutionMatrix = noConvolutionMatrix; /* select which convolution to use */

    renderCanvas()
    canvas.addEventListener('mousedown', mousedownHandler)
    canvas.addEventListener('mousemove', moveHandler)
    window.onmousewheel = document.onmousewheel = mousewheelHandler

    brush.style.border = "none"
    eraser.style.border = "none"

    brush.addEventListener("click", brushClick)
    eraser.addEventListener("click", eraserClick)
    color.addEventListener("change", colorChange)
    size.addEventListener("change", sizeChange)
    text.addEventListener("keyup", addText)
    reset.addEventListener("click", resetClick)
    saveLink.addEventListener("click", saveClick)

    let uploaderImg = document.getElementById('fileInput')
    uploaderImg.addEventListener('change', handleImage, false)
}

// reference - https://stackoverflow.com/questions/10906734/how-to-upload-image-into-html5-canvas
function handleImage(e) {
    let reader = new FileReader()
    reader.onload = function (event) {
        let img = new Image()

        img.onload = function () {
            images.push({"src": img, "x": 0, "y": 0, "width": img.width, "height": img.height, "rotation": 0})
            console.log(images)
            ctx.drawImage(img, 0, 0);
            noImgUploaded = false
            imageLayers()
        }
        img.src = event.target.result
    }
    reader.readAsDataURL(e.target.files[0])
}

function addText() {
    textColor = document.getElementById("recentColor")
    scribbleCanvasCtx.fillStyle = textColor.value;
    recentText = text.value
    recentSize = size.value
    scribbleCanvasCtx.font = `${recentSize}px Arial`
    scribbleCanvasCtx.fillText(recentText, 150, 150);
    renderCanvas()
}

function colorChange() {
    recentColor = color.value
    scribbleCanvasCtx.strokeStyle = recentColor
}

function sizeChange() {
    recentSize = size.value
    scribbleCanvasCtx.lineWidth = recentSize
}

function brushClick() {
    brushColor = document.getElementById("recentColor")
    scribbleCanvasCtx.strokeStyle = brushColor.value;
    brush.style.border = "2px solid red"
    eraser.style.border = "none"
    canvas.addEventListener("mousedown", brushDown, false)
    canvas.addEventListener("mousemove", brushMove, false)
    canvas.addEventListener("mouseup", brushUp, false)

    toggleBrush()
    toggleEraser()
}

function brushDown(e) {
    canvas.style.cursor = "crosshair"
    if (!brushIsSet) {
        return
    }

    if (e.which === 1) {
        let canvasBoundingRectangle = canvas.getBoundingClientRect()
        mouseX = e.clientX - canvasBoundingRectangle.left
        mouseY = e.clientY - canvasBoundingRectangle.top

        scribbleCanvasCtx.beginPath()
        scribbleCanvasCtx.moveTo(mouseX, mouseY)
        scribbleCanvasCtx.lineTo(mouseX, mouseY)
        scribbleCanvasCtx.stroke()

        renderCanvas()
    }
}

function brushUp() {
    canvas.style.cursor = "default"
}
//
//function brushDraw(e) {
//    if (!brushIsSet) {
//        return
//    }
//
//
//}

function brushMove(e) {
    if (e.which === 1) {
        let canvasBoundingRectangle = canvas.getBoundingClientRect()
        mouseX = e.clientX - canvasBoundingRectangle.left
        mouseY = e.clientY - canvasBoundingRectangle.top
    }
//    brushDraw(e)
}

function eraserClick() {
    scribbleCanvasCtx.strokeStyle = "#23262a"
    eraser.style.border = "2px solid red"
    brush.style.border = "none"

    canvas.addEventListener("mousedown", brushDown, false)
    canvas.addEventListener("mousemove", brushMove, false)
    canvas.addEventListener("mouseup", brushUp, false)
    toggleBrush()
    toggleEraser()
}

function resetClick() {
    window.location.reload()
}

function saveClick() {
    let data = canvas.toDataURL()
    console.log(data)
    saveLink.href = data
    saveLink.download = "kelvin.png"
}

function updateTextInput(val) {
    document.getElementById('textInput').value = val
}

function checkAlphaOn(){
    if(alphaOff){
        renderCanvas()
    }
}

function renderCanvasForAplhaComposits(){
    
    ctx.clearRect(0, 0, canvas.width, canvas.height)

    selector = document.getElementById('selection')
    selectedAlpha = selector.options[selector.selectedIndex].value
    if(selectedAlpha == "none"){
        alphaOff= true
       

        renderCanvas()
    }else{
        alphaOff = false
    }
      
    ctx.fillStyle = "orange" 
    ctx.fillRect(100, 0, 200, 300)

    ctx.globalCompositeOperation = selectedAlpha

    ctx.drawImage(images[1].src, 0, 100, 400, 400)
}

function renderCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)

    images.map((image, index) => {
        offscreenCanvasCtx.clearRect(0, 0, canvas.width, canvas.height)

        // highlight current image
        if (index === currentImageIndex) {
            offscreenCanvasCtx.fillStyle = "red"
            offscreenCanvasCtx.fillRect(image.x - 2, image.y - 2, image.width + 4, image.height + 4)
        }

        // draw image
        offscreenCanvasCtx.drawImage(image.src, image.x, image.y, image.width, image.height)


        // rotate image
        ctx.save()
        ctx.translate((image.x + image.width / 2), (image.y + image.height / 2))
        ctx.rotate(Math.radians(image.rotation))
        ctx.translate(-(image.x + image.width / 2), -(image.y + image.height / 2))

        // If Statements for Canvas Filters
        // Brightness Filter
        if (image.brightness) {
            imageData = offscreenCanvasCtx.getImageData(image.x, image.y, image.width, image.height)

            for (let i = 0; i < imageData.data.length; i += 4) {
                imageData.data[i] *= image.brightness
                imageData.data[i + 1] *= image.brightness
                imageData.data[i + 2] *= image.brightness
            }
            offscreenCanvasCtx.putImageData(imageData, image.x, image.y)
        }

        // Gray Scale Filter
        if (image.grayScale) {
            imageData = offscreenCanvasCtx.getImageData(image.x, image.y, image.width, image.height)

            for (let i = 0; i < imageData.data.length; i += 4) {

                grayscale = (imageData.data[i + 0] + imageData.data[i + 1] + imageData.data[i + 2]) / 3
                imageData.data[i + 0] = grayscale
                imageData.data[i + 1] = grayscale
                imageData.data[i + 2] = grayscale
                // imageData.data[i + 3] = 255
            }
            offscreenCanvasCtx.putImageData(imageData, image.x, image.y)
        }

        // Sepia Filter
        if (image.sepia) {
            imageData = offscreenCanvasCtx.getImageData(image.x, image.y, image.width, image.height)

            for (let i = 0; i < imageData.data.length; i += 4) {
                red = imageData.data[i]
                green = imageData.data[i + 1]
                blue = imageData.data[i + 2]

                imageData.data[i] = (red * 0.393) + (green * 0.769) + (blue * 0.189)
                imageData.data[i + 1] = (red * 0.349) + (green * 0.686) + (blue * 0.168)
                imageData.data[i + 2] = (red * 0.272) + (green * 0.534) + (blue * 0.131)
            }
            offscreenCanvasCtx.putImageData(imageData, image.x, image.y)
        }

        // Invert Filter
        if (image.invert) {
            imageData = offscreenCanvasCtx.getImageData(image.x, image.y, image.width, image.height)

            for (let i = 0; i < imageData.data.length; i += 4) {
                imageData.data[i + 0] = 255 - imageData.data[i + 0]
                imageData.data[i + 1] = 255 - imageData.data[i + 1]
                imageData.data[i + 2] = 255 - imageData.data[i + 2]
                imageData.data[i + 3] = 255;
            }
            offscreenCanvasCtx.putImageData(imageData, image.x, image.y)
        }

        // Posterise Filter
        if (image.posterise) {
            imageData = offscreenCanvasCtx.getImageData(image.x, image.y, image.width, image.height)

            for (let i = 0; i < imageData.data.length; i += 4) {
                imageData.data[i + 0] = imageData.data[i + 0] - imageData.data[i + 0] % 64
                imageData.data[i + 1] = imageData.data[i + 1] - imageData.data[i + 1] % 64
                imageData.data[i + 2] = imageData.data[i + 2] - imageData.data[i + 2] % 64
                imageData.data[i + 3] = 255
            }
            offscreenCanvasCtx.putImageData(imageData, image.x, image.y)
        }

        // Threshold Filter
        if (image.threshold) {
            imageData = offscreenCanvasCtx.getImageData(image.x, image.y, image.width, image.height)

            for (let i = 0; i < imageData.data.length; i += 4) {
                for (let rgb = 0; rgb < 3; rgb++) {
                    if (imageData.data[i + rgb] < 128) {
                        imageData.data[i + rgb] = 0;
                    } else {
                        imageData.data[i + rgb] = 255;
                    }
                }
                imageData.data[i + 3] = 255;
            }
            offscreenCanvasCtx.putImageData(imageData, image.x, image.y)
        }

        // Alpha Composites
        if (image.composit) {
            
            // define the shadow offset and colour
            let offset = 10;
            let shadowColour = '#ff0';

            // 1) define the alpha area    
            doubleBufferCtx.drawImage(alphaImage, 10, 10, 350, 350);

            // 2) select the alpha composite   
            doubleBufferCtx.globalCompositeOperation = 'source-in';

            // 3) draw the original image
            // only the part that overlaps the alpha area will be visible
            doubleBufferCtx.beginPath();
            doubleBufferCtx.fillStyle = shadowColour;
            doubleBufferCtx.fillRect(0, 0, width, height);
            doubleBufferCtx.closePath();

            // draw the saved shadow image onto the canvas slightly below and to the right 
            // of where the actual image will be drawn
            ctx.drawImage(doubleBuffer, 0, 0, width + offset, height + offset);

            // draw the image slightly above and to the left of the shadow
            // this is similar to the code above, except now we draw the
            // original image rather than its shadow
            doubleBufferCtx.globalCompositeOperation = 'source-over';

            // 1) define the alpha area
            doubleBufferCtx.drawImage(alphaImage, 10, 10, 350, 350);

            // 2) select the alpha operation    
            doubleBufferCtx.globalCompositeOperation = 'source-in';

            // 3) draw the original image
            // only the part that overlaps the alpha area will be visible
            doubleBufferCtx.drawImage(imageData, 0, 0, width, height);

            // draw the saved image onto the canvas slightly below and to the right 
            // of where the actual image will be drawn   
            ctx.drawImage(doubleBuffer, 0, 0, width, height);
        }

        // Image Convolutions
        // draw the original image into the double buffer
        doubleBufferCtx.drawImage(image.src, image.x, image.y, image.width, image.height)

        // get the image data (i.e. the pixels) from the double buffer
        imageData = doubleBufferCtx.getImageData(images[currentImageIndex].x, images[currentImageIndex].y, images[currentImageIndex].width, images[currentImageIndex].height)
        data = imageData.data

        convolutionAmount = 0
        for (let j = 0; j < 9; j++) {
            convolutionAmount += convolutionMatrix[j]
        }

        originalImageData = imageData
        originalData = originalImageData.data;

        for (let i = 0; i < data.length; i += 4) {
            data[ i + 3] = 255;

            for (let rgbOffset = 0; rgbOffset < 3; rgbOffset++) {
                let convolutionPixels = [originalData[i + rgbOffset - width * 4 - 4],
                    originalData[i + rgbOffset - width * 4],
                    originalData[i + rgbOffset - width * 4 + 4],
                    originalData[i + rgbOffset - 4],
                    originalData[i + rgbOffset],
                    originalData[i + rgbOffset + 4],
                    originalData[i + rgbOffset + width * 4 - 4],
                    originalData[i + rgbOffset + width * 4],
                    originalData[i + rgbOffset + width * 4 + 4]];

                // do the convolution
                convolvedPixel = 0
                for (let j = 0; j < 9; j++) {
                    convolvedPixel += convolutionPixels[j] * convolutionMatrix[j]
                }
                if (convolutionMatrix === embossConvolutionMatrix) {
                    data[i + rgbOffset] = convolvedPixel + 127
                } else {
                    convolvedPixel /= convolutionAmount
                    data[i + rgbOffset] = convolvedPixel
                }
            }
        }
        ctx.putImageData(imageData, images[currentImageIndex].x, images[currentImageIndex].y)

        // Draw the manipulated offscreen image onto the display canvas
        ctx.drawImage(offscreenCanvas, 0, 0, canvas.width, canvas.height)

        // drawImage  scribble double buffer
        ctx.drawImage(scribbleCanvas, 0, 0, canvas.width, canvas.height)
        ctx.restore()
    })
    // brushClick()
    //eraserClick()
}

function mousedownHandler(e) {
    if (e.which === 1) {  // left mouse button
        let canvasBoundingRectangle = canvas.getBoundingClientRect()
        mouseX = e.clientX - canvasBoundingRectangle.left
        mouseY = e.clientY - canvasBoundingRectangle.top

        for (let i = images.length - 1; i > -1; i--) {
            if (mouseIsInsideImage(images[i].x, images[i].y, images[i].width, images[i].height, mouseX, mouseY)) {
                offsetX = mouseX - images[i].x
                offsetY = mouseY - images[i].y
                currentImageIndex = i
                renderCanvas()

                // set the HTML inputs
                document.getElementById("rotation").value = images[currentImageIndex].rotation
                break
            }
        }
    }
}

function moveHandler(e) {
    if ((currentImageIndex !== null) && (e.which === 1)) {  // left mouse button
        let canvasBoundingRectangle = canvas.getBoundingClientRect()
        mouseX = e.clientX - canvasBoundingRectangle.left
        mouseY = e.clientY - canvasBoundingRectangle.top
        if (brushIsSet) {
            scribbleCanvasCtx.lineTo(mouseX, mouseY)
            scribbleCanvasCtx.stroke()
            canvas.style.cursor = "crosshair"
        } else {
            images[currentImageIndex].x = mouseX - offsetX
            images[currentImageIndex].y = mouseY - offsetY
        }
        renderCanvas()
    }
}

function mousewheelHandler(e) {
    if (currentImageIndex !== null) {
        let canvasBoundingRectangle = canvas.getBoundingClientRect()
        mouseX = e.clientX - canvasBoundingRectangle.left
        mouseY = e.clientY - canvasBoundingRectangle.top
        //  if (mouseIsInsideImage(imageX, imageY, imageWidth, imageHeight, mouseX, mouseY))
        {
            let oldCentreX = images[currentImageIndex].x + (images[currentImageIndex].width / 2)
            let oldCentreY = images[currentImageIndex].y + (images[currentImageIndex].height / 2)

            images[currentImageIndex].width += e.wheelDelta / 120
            images[currentImageIndex].height += e.wheelDelta / 120

            images[currentImageIndex].x = oldCentreX - (images[currentImageIndex].width / 2)
            images[currentImageIndex].y = oldCentreY - (images[currentImageIndex].height / 2)

            renderCanvas()
        }
    }
}

function mouseIsInsideImage(imageTopLeftX, imageTopLeftY, imageWidth, imageHeight, x, y) {
    if (brushIsSet) {
        return
    }
    if ((x > imageTopLeftX) && (y > imageTopLeftY)) {
        if (x > imageTopLeftX) {
            if ((x - imageTopLeftX) > imageWidth) {
                return false // to the right of the image
            }
        }
        if (y > imageTopLeftY) {
            if ((y - imageTopLeftY) > imageHeight) {
                return false // below the image
            }
        }
    } else { // above or to the left of the image
        return false
    }
    return true // inside image
}

// Rotation 
function setRotationDegrees(newRotationDegrees) {
    images[currentImageIndex].rotation = parseInt(newRotationDegrees)
    renderCanvas()
}

// Brightness Filter
function setBrightness(newBrightness) {
    images[currentImageIndex].brightness = newBrightness;
    renderCanvas()
}

// Grayscale Filter
function setGrayscale(newGrayValue) {
    images[currentImageIndex].grayScale = newGrayValue
    renderCanvas()
}

// Sepia Filter
function setSepia(newSepiaValue) {
    images[currentImageIndex].sepia = newSepiaValue
    renderCanvas()
}

// Invert Filter
function setInvert(newInvertValue) {
    images[currentImageIndex].invert = newInvertValue
    renderCanvas()
}

// Posterise Filter
function setPosterise(newPosteriseValue) {
    images[currentImageIndex].posterise = newPosteriseValue
    renderCanvas()
}

// Threshold Filter
function setThreshold(newThresholdValue) {
    images[currentImageIndex].threshold = newThresholdValue
    renderCanvas()
}

// Alpha Composit
function setComposit(alphaComposit) {
    images[currentImageIndex].composit = alphaComposit
    renderCanvas()
}

// Convolution Matrix
function setConvolution(matrix) {
    convolutionMatrix = matrix
    renderCanvas()
}

// Convert from degrees to radians.
Math.radians = function (degrees) {
    return degrees * Math.PI / 180
}

function toggleBrush() {
    if (brushIsSet === true) {
        brushIsSet = false
        brush.style.border = "none"
    } else {
        brushIsSet = true
    }
}

function toggleEraser() {
    if (eraserIsSet === true) {
        eraserIsSet = false
        eraser.style.border = "none"
        brushIsSet = false
    } else {
        eraserIsSet = true
    }
}

function mainBackdrop() {
    var mainDiv = document.getElementById("mainFrame")
    mainDiv.className = "mainFrame_animate"
}


function deleteLayer(a) {

    // images.remove(a)
    // console.log(a)

    if (a > -1) {
        images.splice(a, 1);
    }
    imageLayers()
    renderCanvas()
}

function moveLayerUp(a) {
    if (a >= 0 && a < Object.keys(images).length - 1) {
        let dumper = images[a + 1] 
        images[a + 1] = images[a]
        images[a] = dumper
        imageLayers()
        renderCanvas()
    }else{
        console.log("failed")
    }
}

//top layers is 2
//mid is 1
//bottom is 0
//to move up the layer i have to incease index by one 
//and to move down i need to decrease the  layer
//a layer cant move more 

function moveLayerDown(a) {
    if (a != 0) {
        let dumper = images[a - 1]
        images[a - 1] = images[a]
        images[a] = dumper
        imageLayers()
        renderCanvas()
    }
}

function imageLayers() {
    document.getElementById("imagesShow").innerHTML = `<h1>Layers:</h1>`
    let i = 0

    console.log(Object.keys(images).length - 1)

    for (let i = Object.keys(images).length - 1; i >= 0; i--) {
        document.getElementById("imagesShow").innerHTML +=
                `<div class="imageLayer">
                <img class="innerImg" src="${(images[i].src).src}" width="100" height="120">
                <div class="innerImg" id="hoverOptions">
                    <div class="layerOpt"   id="layerUp" onclick="moveLayerUp(${i})">UP</div>
                    <div class="layerOpt"   id="layerDown" onclick="moveLayerDown(${i})" >DOWN</div>
                    <div class="layerOpt deleteLayer" onclick="deleteLayer(${i})" >DEL</div>
                </div>
         </div>`
    }

    if (noImgUploaded) {
        document.getElementById("imagesShow").innerHTML += `
                    <div class="imageLayer image-upload" id="filer">
                        <label for="fileInput">
                            <img class="innerImg" src="images/add.png" width="100" height="120"/>
                        </label>

                        <input id="fileInput" accept="image/png, image/gif, image/jpeg" type="file" />
                    </div>`
    }
}
imageLayers()