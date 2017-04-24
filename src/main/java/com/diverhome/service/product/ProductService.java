package com.diverhome.service.product;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.diverhome.dao.DaoSupport;
import com.diverhome.entity.PageData;
import com.diverhome.entity.TbProduct;
import com.diverhome.service.base.BaseService;
import com.diverhome.util.Constant;
import com.diverhome.util.ImageAnd64Binary;
import com.diverhome.util.StringUtil;

@Service 
public class ProductService extends BaseService {
	
	@Resource(name = "daoSupport") 
	private DaoSupport dao; 
	
	/**
	 * 
	 * @Title: getProductList
	 * @Description: 查询产品
	 * @return	TbSystemUser
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<TbProduct> getProductList(PageData pd) throws Exception {
		return (ArrayList<TbProduct>) dao.findForList("TbProductMapper.getProductList", pd);
	}
	
	/**
	 * 
	 * @Title: getProductById
	 * @Description: 根据id查询产品
	 * @param pd
	 * @return
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public TbProduct getProductById(PageData pd) throws Exception {
		return (TbProduct) dao.findForObject("TbProductMapper.getProductById", pd);
	}

	/**
	 * 
	 * @Title: updateProduct
	 * @Description: 更新产品信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateProduct(PageData pd) throws Exception {
		// 准备入库数据
		prepareData(pd);
		dao.update("TbProductMapper.updateProduct", pd);
	}

	/**
	 * 
	 * @Title: addProduct
	 * @Description: 新增产品
	 * @param pd
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addProduct(PageData pd) throws Exception {
		// 准备入库数据
		prepareData(pd);
		dao.save("TbProductMapper.addProduct", pd);
	}
	
	/**
	 * 
	 * @Title: prepareData
	 * @Description: 准备入库数据
	 * @param pd
	 * @throws
	 */
	private void prepareData(PageData pd)  {
		String imgData = pd.getString("img_url");
		String imgUrl = handleImg(imgData);
		if (!"".equals(imgUrl)) {
			// 替换pd的img_url字段为原尺寸图片的url
			pd.put("img_url", imgUrl);
		}
		String thumbnailImgData = pd.getString("thumbnail_img_url");
		String thumbnailImgUrl = handleImg(thumbnailImgData);
		if (!"".equals(thumbnailImgUrl)) {
			// 替换pd的thumbnail_img_url字段为压缩图片的url
			pd.put("thumbnail_img_url", thumbnailImgUrl);
		}
	}
	
	/**
	 * 
	 * @Title: handleImg
	 * @Description: 处理上传的图片
	 * @param imgData
	 * @return
	 * @throws
	 */
	private String handleImg(String imgData) {
		String imgUrl = "";
		if (!"".equals(imgData)) {
			// 取出图片后缀
			String ext = imgData.substring(imgData.indexOf("/") + 1, imgData.indexOf(";"));
			// 取出图片数据
			String data = imgData.substring(imgData.indexOf(",") + 1);
			// 生成文件名
			String fileName = StringUtil.getSequence() + "." + ext;
			// 文件在服务器上的绝对路径
			String filePath = System.getProperty("app.root") + Constant.PRODUCT_IMG_PATH + File.separator + fileName;
			// 保存图片到服务器
			if (ImageAnd64Binary.generateImage(data, filePath)) {
				imgUrl = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
						.getContextPath() + File.separator + Constant.PRODUCT_IMG_PATH + File.separator + fileName;
			}
		}
		return imgUrl;
	}
	
	/**
	 * 
	 * @Title: getBestSellProductList
	 * @Description: 查询畅销产品Top10
	 * @return	
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ArrayList<PageData> getBestSellProductList() throws Exception {
		return (ArrayList<PageData>) dao.findForList("TbProductMapper.getBestSellProductList", null);
	}
}