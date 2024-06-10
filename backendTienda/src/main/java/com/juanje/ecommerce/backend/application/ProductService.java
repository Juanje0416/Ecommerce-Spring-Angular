package com.juanje.ecommerce.backend.application;

import com.juanje.ecommerce.backend.domain.model.Product;
import com.juanje.ecommerce.backend.domain.port.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
public class ProductService {
    private final IProductRepository iProductRepository;
    private final UploadFileService uploadFileService;

    public ProductService(IProductRepository iProductRepository, UploadFileService uploadFileService) {
        this.iProductRepository = iProductRepository;
        this.uploadFileService = uploadFileService;
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        //aquí manejo las imagenes
        if(product.getId()!=0){//cuando es un producto modificado
            if(multipartFile==null){
                //Si no se proporciona una nueva imagen mantengo la imagen existente
                product.setUrlImage(product.getUrlImage());
            }else{
                //Proceso la imagen nueva que pone el usuario
                String nameFile = product.getUrlImage().substring(29);
                log.info("este es el nombre de la imagen: {}", nameFile);
                //elimino  la imagen existente si no es la imagen default
                if (!nameFile.equals("default.jpg")){
                    uploadFileService.delete(nameFile);
                }
                //cargo la nueva imagen y establezco la URL de la imagen en el producto
                product.setUrlImage(uploadFileService.upload(multipartFile));
            }
        }else{//cuando es un producto nuevo
            product.setUrlImage(uploadFileService.upload(multipartFile));
        }

        return this.iProductRepository.save(product);
    }

    public Iterable<Product> findAll(){
        return this.iProductRepository.findAll();
    }

    public Product findById(Integer id){
        return this.iProductRepository.findById(id);
    }

    public void deleteById(Integer id){
        Product product = findById(id);
        String nameFile = product.getUrlImage().substring(29);

        //puedo hacer seguimiento de mis datos con el inspeccionar
        log.info("este es el nombre de la imagen: {}", nameFile);

        //si el nombre del archivo de imagen no es default elimino el archivo de imagen
        if (!nameFile.equals("default.jpg")){
            uploadFileService.delete(nameFile);
        }
        //lo elimino en la BBDD
        this.iProductRepository.deleteById(id);
    }

    public Iterable<Product> findByCategoryId(Integer categoryId) {
        return this.iProductRepository.findByCategoryId(categoryId);
    }


}
