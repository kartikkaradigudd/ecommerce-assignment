package com.ecommerce.services.cart;

import com.ecommerce.model.AddToCartRequest;
import com.ecommerce.model.Cart;
import com.ecommerce.model.RemoveProductFromCartRequest;
import com.ecommerce.responses.cart.CartResponse;
import com.ecommerce.responses.common.StatusResponse;
import com.ecommerce.services.cart.intefaces.CartService;
import com.ecommerce.storage.CartStorage;
import com.ecommerce.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartStorage cartStorage;

    @Override
    public CartResponse getCartDetailsByUsername(String username) {
        CartResponse response=new CartResponse();
        try {
            response.setCart(cartStorage.getCarts().get(username));
            response.setStatus(Constants.ResponseConstants.OK);
            response.setMessage("Fetched cart details successfully");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(Constants.ResponseConstants.FAILURE);
            response.setMessage("Failed to fetch cart information");
        }
        return response;
    }

    @Override
    public StatusResponse removeProductFromCart(RemoveProductFromCartRequest request) {
        StatusResponse response=new StatusResponse();
        try {
            removeProduct(request);
            response.setStatus(Constants.ResponseConstants.OK);
            response.setMessage("Product removed from cart");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(Constants.ResponseConstants.FAILURE);
            response.setMessage("Failed to remove product");
        }
        return response;
    }

    @Override
    public StatusResponse addToCart(AddToCartRequest request) {
        StatusResponse response=new StatusResponse();
        try {
            addProduct(request);
            response.setStatus(Constants.ResponseConstants.OK);
            response.setMessage("Successfully added product to the cart");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(Constants.ResponseConstants.FAILURE);
            response.setMessage("Failed to add product to Cart");
        }
        return response;
    }

    private void removeProduct(RemoveProductFromCartRequest request){
         Cart cart = cartStorage.getCarts().get(request.getUsername());
         if(cart!=null){
             cart.getProducts().removeIf(product -> product.getId().equals(request.getProductId()));
             cartStorage.addToCart(request.getUsername(),cart);
         }
    }

    private void addProduct(AddToCartRequest request){
        Cart cart=cartStorage.getCarts().get(request.getUsername());
        if(cart!=null){
            cart.getProducts().add(request.getProduct());
            cartStorage.addToCart(request.getUsername(),cart);
        }
    }
}
