package controllers;

import entity.Film;
import entity.Image;
import entity.UploadedFile;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import controllers.validator.UploadedFileValidator;
import service.FilmServiceInterface;
import service.ImageServiceInterface;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Александр Горшов on 24.05.2017.
 */
@Controller
@SessionAttributes("filename")
public class UploadController {
    private static final Logger logger = Logger.getLogger(UploadController.class);

    @Autowired
    private UploadedFileValidator uploadedFileValidator;

    @Autowired
    private FilmServiceInterface filmServiceInterface;

    @Autowired
    private ImageServiceInterface imageServiceInterface;

    @RequestMapping(value = "/film/add/image/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView addImageForFilm(@ModelAttribute Image image, @PathVariable("id") long id,
                                        Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            //validateImage(image.getFile());
            MultipartFile file = image.getFile();
            Random random = new Random();
            int randomValue = random.nextInt();
            String path = request.getSession().getServletContext().getRealPath(File.separator + "media" + File.separator +
                    File.separator + randomValue + file.getOriginalFilename() + ".jpg");
            String dbPath = "media/" + randomValue + file.getOriginalFilename() + ".jpg";
            Film film = (Film) filmServiceInterface.getById(Film.class, id);
            if (film.getImage() == null) {
                image.setId(null);
            } else {
                image.setId(film.getImage().getId());
            }
            saveImage(file, path);
            image.setImagePath(dbPath);
            image.setStatus("main");
            film.setImage(image);
            filmServiceInterface.saveOrUpdate(film);
            RedirectView redirectView = new RedirectView("/film/add/image/" + id);
            redirectView.setStatusCode(HttpStatus.FOUND);
            modelAndView.setView(redirectView);
            modelAndView.addObject("fileName", file.getOriginalFilename());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            model.addAttribute("msg", "error.messages.uploaded");
        }
        modelAndView.addObject("msg", "Upload success");
        return modelAndView;
    }

    private void validateImage(MultipartFile img) throws IOException {
        if (!img.getContentType().equals("image/jpeg")) { //TODO Here nullpointer{
            throw new IOException("iamge error");
        }

    }

    private void saveImage(MultipartFile image, String path) throws IOException {
        File file = new File(path);
        try {
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new IOException("error image");
        }
    }

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

    @RequestMapping(value = "/film/add/image/{id}", method = RequestMethod.GET)
    public String addImageForFilm(@PathVariable("id") long id) {
        return "redirect:/films/" + id;
    }
}
