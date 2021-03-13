export default class s1_miCampo extends Phaser.Scene {
    constructor() {
        super({key: "s1_miCampo", active: true});
    }
    preload() {

    } 
    create() {
        let graphics = this.add.graphics();

        graphics.fillStyle(0x4048bd, 1);

        graphics.fillRect(global_var_s1_x, global_var_s1_y, global_var_s1_w, global_var_s1_h);

        this.add.text(global_var_s1_x, global_var_s1_y, "s1_miCampo", { font: "16px Courier", fill: "#81d8d0" });

    }
    update(time, delta) {
        
    }
}
