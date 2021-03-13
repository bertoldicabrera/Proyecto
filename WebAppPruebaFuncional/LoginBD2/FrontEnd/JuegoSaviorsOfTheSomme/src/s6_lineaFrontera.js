export default class s6_lineaFrontera extends Phaser.Scene {
    constructor() {
        super({key: "s6_lineaFrontera", active: true});
    }
    preload() {

    } 
    create() {
        let graphics = this.add.graphics();

        graphics.fillStyle(0x213859, 1);

        graphics.fillRect(global_var_s6_x, global_var_s6_y, global_var_s6_w, global_var_s6_h);

        this.add.text(global_var_s6_x, global_var_s6_y, "s6_lineaFrontera agua profunda", { font: "16px Courier", fill: "#9fd5d1" });

    }
    update(time, delta) {
        
    }
}
