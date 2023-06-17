package app.facade;

import app.dto.rq.MessageRequestDTO;
import app.dto.rs.MessageResponseDTO;
import app.exceptions.httpError.BadRequestException;
import app.model.Message;
import app.service.MessageService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MessageFacade extends GeneralFacade<Message, MessageRequestDTO, MessageResponseDTO> {

  @Autowired
  private MessageService messageService;

  public MessageResponseDTO addMessageToChat(Long userId, Message message) {
    if (message.getUser().getId().equals(userId))
      return this.convertToDto(this.messageService.addMessage(message));
    else throw new BadRequestException(String.format("Current user with id: %d is not the author of message ", userId));
  }

  public boolean changeMessage(Long userId, Message message){
    return this.messageService.changeMessage(userId, message);
  }

  public boolean deleteMessage(Long userId, MessageRequestDTO message) {
    return this.messageService.deleteMessage(userId, message.getId());
  }
}
