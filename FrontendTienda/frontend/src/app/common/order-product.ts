export class OrderProduct {

    constructor(
        public id:number|null, //le especifico que también puede ser nula ya que cuando ingreso una entidad a la tabla de bbdd
        // esta no lleva un valor sino que se lo genera de forma automatica, llevará la id en el caso de editar
        public productId:number,
        public quantity:number,
        public price:number
    ){}

}
