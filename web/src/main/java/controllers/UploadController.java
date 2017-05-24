package controllers;

import entity.UploadedFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import controllers.validator.UploadedFileValidator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Александр Горшов on 24.05.2017.
 */
@Controller
@SessionAttributes("filename")
public class UploadController {
    private static final Logger logger = Logger.getLogger(UploadController.class);

    @Autowired
    private UploadedFileValidator uploadedFileValidator;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView uploadFile(@ModelAttribute("uploadedFile") @Validated UploadedFile uploadedFile, BindingResult result, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        String fileName = null;
        MultipartFile file = uploadedFile.getFile();
        uploadedFileValidator.validate(uploadedFile, result);
        if (result.hasErrors()) {
            modelAndView.setViewName("main");
        } else {
            try {
                byte[] bytes = file.getBytes();
                fileName = file.getOriginalFilename();
                String rootPath = "C:\\path\\";
                File dir = new File(rootPath + File.separator + "loadFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File loadFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(loadFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                logger.info("uploaded: " + loadFile.getAbsolutePath());
                RedirectView redirectView = new RedirectView("fileuploaded");
                redirectView.setStatusCode(HttpStatus.FOUND);
                modelAndView.setView(redirectView);
                modelAndView.addObject("filename", fileName);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                model.addAttribute("msg", "error.messages.uploaded");
            }
        }
        modelAndView.addObject("msg", "Upload success");
        return modelAndView;
    }

    @RequestMapping(value = "/fileuploaded", method = RequestMethod.GET)
    public String fileUploaded() {
        return "redirect:/users";
    }
}
